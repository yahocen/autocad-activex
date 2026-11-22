"""
映射类属性类型校验：Java getter/setter 的类型 vs AutoCAD 2024 类型库中属性的真实类型。

extract_tlb.py 早期把 VT_USERDEFINED(29) 一律当作 int，而 AutoCAD 大量属性用的是别名类型
（ACAD_ANGLE / ACAD_DISTANCE / ACAD_NOUNITS = double，ACAD_LTYPE / ACAD_LAYER = BSTR，
ACAD_COLOR / AcLineWeight 等 = 枚举 int ...），由此生成的 getter 会用错 Variant 取值方法，
运行时抛 "getInt() only legal on Variants of type VariantInt, not 5" 之类的异常。

本脚本直接读类型库并展开别名，逐个检查 io.github.yahocen.acad 下映射类中形如
    public T getXxx() { return getProperties("Xxx").getYyy(); }
    public R setXxx(T v) { setProperties("Xxx", v); ... }
的成员，并检查带参属性（如 Coordinate(Index)）是否被错误地映射成无参 getter/setter。

用法: python bin/codegen/verify_types.py     有问题时退出码 1
"""
import os
import re
import sys

import pythoncom

_HERE = os.path.dirname(os.path.abspath(__file__))
_ROOT = os.path.abspath(os.path.join(_HERE, '..'))
TLB = r'C:\Program Files\Common Files\Autodesk Shared\acax24enu.tlb'
SRC_DIRS = [os.path.join(_ROOT, 'src', 'main', 'java', 'io', 'github', 'yahocen', 'acad', 'document', d)
            for d in ('element', 'base', 'manager')]

# Java 映射类 -> 类型库接口（与 verify_entities.py 一致，其余按 'Acad' + 类名匹配）
CLASS_TO_TLB = {
    'Face3D': 'Acad3DFace', 'Polyline3D': 'Acad3DPolyline', 'Solid3D': 'Acad3DSolid',
    'PointEntity': 'AcadPoint', 'Solid2D': 'AcadSolid', 'XLine': 'AcadXline',
    'AcadEntity': 'AcadEntity', 'AcadObject': 'AcadObject',
    'PdfUnderlay': 'AcadUnderlay', 'DgnUnderlay': 'AcadUnderlay',
    'Box': 'Acad3DSolid', 'Cone': 'Acad3DSolid', 'Cylinder': 'Acad3DSolid',
    'Sphere': 'Acad3DSolid', 'Torus': 'Acad3DSolid', 'Rectangle': 'AcadLWPolyline',
    'LineType': 'AcadLineType', 'UCS': 'AcadUCS', 'UCSs': 'AcadUCSs',
}

VT_EMPTY, VT_I2, VT_I4, VT_R4, VT_R8, VT_DATE, VT_BSTR, VT_DISPATCH, VT_BOOL, VT_VARIANT, VT_UNKNOWN = \
    0, 2, 3, 4, 5, 7, 8, 9, 11, 12, 13
VT_I1, VT_UI1, VT_UI2, VT_UI4, VT_INT, VT_UINT, VT_HRESULT, VT_PTR, VT_USERDEFINED = \
    16, 17, 18, 19, 22, 23, 25, 26, 29
VT_ARRAY = 0x2000

VT_I8 = 20  # LONG_PTR：x64 上为 64 位，用 getLong()/long
INT_VTS = {VT_I1, VT_UI1, VT_I2, VT_UI2, VT_I4, VT_UI4, VT_INT, VT_UINT}

# 类型库自身不一致、以 Autodesk 文档和运行时行为为准的成员
KNOWN_EXCEPTIONS = {
    # 类型库里 put 参数声明为 INT，但 get 返回 BSTR，文档为 String 读写属性
    'PointCloudEx.setColorScheme(String)',
}


def resolve(ti, tdesc):
    """展开 TYPEDESC，返回 (基本 VT, 显示名)。枚举 -> VT_I4，接口 -> VT_DISPATCH。"""
    if isinstance(tdesc, int):
        return (VT_ARRAY, 'array') if tdesc & VT_ARRAY else (tdesc, str(tdesc))
    vt = tdesc[0]
    if vt == VT_PTR:
        return resolve(ti, tdesc[1])
    if vt & VT_ARRAY or vt == 27:  # SAFEARRAY
        return VT_ARRAY, 'array'
    if vt == VT_USERDEFINED:
        ref = ti.GetRefTypeInfo(tdesc[1])
        name = ref.GetDocumentation(-1)[0]
        attr = ref.GetTypeAttr()
        kind = attr[5]
        if kind == pythoncom.TKIND_ALIAS:
            base, _ = resolve(ref, attr[14])
            return base, name
        if kind == pythoncom.TKIND_ENUM:
            return VT_I4, name
        return VT_DISPATCH, name
    return vt, str(vt)


def load_props():
    """interface -> {prop: {'get': (vt,name)|None, 'put': (vt,name)|None, 'nparams': n}}，含继承。"""
    tlb = pythoncom.LoadTypeLib(TLB)
    own, parents = {}, {}
    for i in range(tlb.GetTypeInfoCount()):
        ti = tlb.GetTypeInfo(i)
        name = tlb.GetDocumentation(i)[0]
        attr = ti.GetTypeAttr()
        if attr[5] not in (pythoncom.TKIND_INTERFACE, pythoncom.TKIND_DISPATCH):
            continue
        # 双接口取 vtable 视图：retval 作为最后一个参数，便于统一处理
        if attr[5] == pythoncom.TKIND_DISPATCH:
            try:
                ti = ti.GetRefTypeInfo(ti.GetRefTypeOfImplType(-1))
                attr = ti.GetTypeAttr()
            except pythoncom.com_error:
                pass
        key = name[1:] if name.startswith('I') and name[1:2] == 'A' else name
        props = {}
        for f in range(attr[6]):
            fd = ti.GetFuncDesc(f)
            pname = ti.GetNames(fd.memid)[0]
            params = fd.args
            if fd.invkind == pythoncom.INVOKE_PROPERTYGET:
                # 最后一个参数是 [out, retval]
                p = props.setdefault(pname, {'get': None, 'put': None, 'nparams': 0})
                p['get'] = resolve(ti, params[-1][0]) if params else (VT_EMPTY, '?')
                p['nparams'] = max(p['nparams'], len(params) - 1)
            elif fd.invkind in (pythoncom.INVOKE_PROPERTYPUT, pythoncom.INVOKE_PROPERTYPUTREF):
                p = props.setdefault(pname, {'get': None, 'put': None, 'nparams': 0})
                p['put'] = resolve(ti, params[-1][0])
                p['nparams'] = max(p['nparams'], len(params) - 1)
        own[key] = props
        if attr[8] > 0:
            try:
                base = ti.GetRefTypeInfo(ti.GetRefTypeOfImplType(0)).GetDocumentation(-1)[0]
                parents[key] = base[1:] if base.startswith('IA') else base
            except pythoncom.com_error:
                pass

    def full(k, seen=()):
        if k not in own or k in seen:
            return {}
        merged = dict(full(parents.get(k), seen + (k,))) if parents.get(k) else {}
        merged.update(own[k])
        return merged

    return {k: full(k) for k in own}


GETTER = re.compile(
    r'public\s+([\w<>\[\]]+)\s+(?:get|is|has)(\w+)\(\)\s*\{\s*return\s+getProperties\("(\w+)"\)(?:\.(\w+)\(\))?\s*;')
SETTER = re.compile(
    r'public\s+[\w<>]+\s+set(\w+)\(([\w<>\[\]]+)\s+\w+\)\s*\{\s*setProperties\("(\w+)",')

ACCESSOR_OK = {
    'getInt': INT_VTS, 'getShort': {VT_I2, VT_UI2, VT_I1, VT_UI1}, 'getLong': INT_VTS | {VT_I8},
    'getDouble': {VT_R8}, 'getFloat': {VT_R4}, 'getString': {VT_BSTR}, 'getBoolean': {VT_BOOL},
    'getDispatch': {VT_DISPATCH, VT_UNKNOWN}, 'toDispatch': {VT_DISPATCH, VT_UNKNOWN, VT_VARIANT},
    'getDate': {VT_DATE}, 'getJavaDate': {VT_DATE},
}
# getInt 只接受 VT_I4：属性声明为 VT_I2 时 jacob 会抛异常
STRICT = {'getInt': {VT_I4, VT_INT}, 'getShort': {VT_I2}}
JAVA_FOR_VT = {VT_R8: {'double', 'Double'}, VT_R4: {'float', 'double'}, VT_BSTR: {'String'},
               VT_BOOL: {'boolean', 'Boolean'}}
for _v in INT_VTS:
    JAVA_FOR_VT[_v] = {'int', 'short', 'long', 'Integer'}
JAVA_FOR_VT[VT_I8] = {'long', 'Long'}


def tlb_of(cls, props):
    for cand in (CLASS_TO_TLB.get(cls), 'Acad' + cls, cls):
        if cand and cand in props:
            return cand
    return None


def main():
    props = load_props()
    issues, checked = [], 0
    for d in SRC_DIRS:
        for fn in sorted(os.listdir(d)):
            if not fn.endswith('.java'):
                continue
            cls = fn[:-5]
            tlb_name = tlb_of(cls, props)
            if not tlb_name:
                continue
            src = open(os.path.join(d, fn), encoding='utf-8').read()
            p = props[tlb_name]
            for m in GETTER.finditer(src):
                jtype, _, prop, accessor = m.groups()
                info = p.get(prop)
                if not info or not info['get']:
                    continue
                checked += 1
                vt, tname = info['get']
                where = f'{cls}.get{m.group(2)}()'
                if info['nparams'] > 0:
                    issues.append(f'{where}: COM 属性 {prop} 需要 {info["nparams"]} 个索引参数，不能无参读取')
                    continue
                if accessor:
                    ok = STRICT.get(accessor, ACCESSOR_OK.get(accessor))
                    if ok is not None and vt not in ok and vt != VT_VARIANT:
                        issues.append(f'{where}: 用 {accessor}() 读取，但 {prop} 的类型是 {tname}(VT={vt})')
                    elif vt in JAVA_FOR_VT and jtype not in JAVA_FOR_VT[vt] and jtype != 'Variant':
                        issues.append(f'{where}: 返回 {jtype}，但 {prop} 的类型是 {tname}(VT={vt})')
            for m in SETTER.finditer(src):
                _, jtype, prop = m.groups()
                info = p.get(prop)
                if not info or not info['put']:
                    continue
                checked += 1
                vt, tname = info['put']
                where = f'{cls}.set{m.group(1)}({jtype})'
                if info['nparams'] > 0:
                    issues.append(f'{where}: COM 属性 {prop} 需要 {info["nparams"]} 个索引参数，不能无参写入')
                elif vt in JAVA_FOR_VT and jtype not in JAVA_FOR_VT[vt] and jtype not in ('Variant', 'Object'):
                    issues.append(f'{where}: 参数为 {jtype}，但 {prop} 的类型是 {tname}(VT={vt})')
    issues = [i for i in issues if i.split(':')[0] not in KNOWN_EXCEPTIONS]
    print(f'已校验 {checked} 个 getter/setter')
    for i in sorted(set(issues)):
        print('  ' + i)
    print(f'问题 {len(set(issues))} 个')
    return 1 if issues else 0


if __name__ == '__main__':
    sys.exit(main())
