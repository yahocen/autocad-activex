"""Extract AutoCAD 2024 ActiveX type library with full type information."""
import pythoncom, json, re, os

# 脚本所在目录（bin/codegen/），数据文件输出到同目录
_HERE = os.path.dirname(os.path.abspath(__file__))

tlb = pythoncom.LoadTypeLib(r'C:\Program Files\Common Files\Autodesk Shared\acax24enu.tlb')

# VARTYPE → Java type mapping
VT_MAP = {
    2: 'short', 3: 'int', 4: 'float', 5: 'double',
    7: 'double', 8: 'String', 9: 'Dispatch', 10: 'int',
    11: 'boolean', 12: 'Variant', 13: 'Dispatch',
    16: 'char', 17: 'byte', 18: 'short', 19: 'int', 22: 'int',
    20: 'long', 21: 'long',
    23: 'int', 24: None, 25: None, 29: 'int', 30: 'int',
}

# 当前正在解析的 ITypeInfo，用于展开 VT_USERDEFINED（见 resolve_userdefined）
_CUR_INFO = None


def resolve_userdefined(href):
    """展开 VT_USERDEFINED：别名取底层类型，枚举为 int，接口/coclass 为 Dispatch。

    AutoCAD 大量属性用别名类型声明（ACAD_ANGLE / ACAD_DISTANCE / ACAD_NOUNITS = double，
    ACAD_LTYPE / ACAD_LAYER = BSTR，LONG_PTR = 64 位整数），早期版本把 29 一律映射成 int，
    导致生成的 getter 用 getInt() 读 double 属性，运行时抛
    "getInt() only legal on Variants of type VariantInt, not 5"。
    """
    if _CUR_INFO is None or not isinstance(href, int):
        return 'Object'
    try:
        ref = _CUR_INFO.GetRefTypeInfo(href)
        attr = ref.GetTypeAttr()
        kind = attr[5]
        if kind == pythoncom.TKIND_ALIAS:
            outer = _set_info(ref)
            try:
                return parse_tdesc(attr[14])[1]
            finally:
                _set_info(outer)
        if kind == pythoncom.TKIND_ENUM:
            return 'int'
        return 'Dispatch'
    except pythoncom.com_error:
        return 'Object'


def _set_info(info):
    global _CUR_INFO
    old, _CUR_INFO = _CUR_INFO, info
    return old

def parse_tdesc(tdesc):
    """Parse a TYPEDESC tuple → (vt_str, java_type, is_array, is_byref)"""
    if isinstance(tdesc, int):
        tdesc = (tdesc,)
    if not isinstance(tdesc, tuple) or len(tdesc) == 0:
        return ('unknown', 'Object', False, False)
    vt = tdesc[0]
    if vt == 29:  # VT_USERDEFINED
        return ('29', resolve_userdefined(tdesc[1] if len(tdesc) > 1 else None), False, False)
    if vt == 26:  # VT_PTR
        inner = tdesc[1] if len(tdesc) > 1 else None
        if isinstance(inner, tuple) and inner[0] == 8192:  # points to array
            inner_vt = inner[1] if isinstance(inner[1], tuple) else (inner[1],)
            base_vt = inner_vt[0] if isinstance(inner_vt, tuple) else inner_vt
            return (f'ptr_array_{base_vt}', VT_MAP.get(base_vt, 'Object'), True, True)
        elif isinstance(inner, tuple):
            base_vt = inner[0]
            if base_vt == 29:
                return ('ptr_29', resolve_userdefined(inner[1] if len(inner) > 1 else None), False, True)
            return (f'ptr_{base_vt}', VT_MAP.get(base_vt, 'Object'), False, True)
        return ('ptr', 'Object', False, True)
    elif vt == 8192:  # VT_ARRAY
        elem = tdesc[1] if len(tdesc) > 1 else 5
        if isinstance(elem, tuple):
            elem = elem[0]
        return (f'array_{elem}', VT_MAP.get(elem, 'Object'), True, False)
    else:
        return (f'{vt}', VT_MAP.get(vt, 'Object'), False, False)

def extract_elemdesc(ed):
    """Extract type info from an ELEMDESC tuple."""
    if not isinstance(ed, (tuple, list)) or len(ed) < 1:
        return {'java_type': 'Object', 'is_array': False, 'is_byref': False}
    tdesc = ed[0]
    vt_info = parse_tdesc(tdesc)
    return {
        'java_type': vt_info[1],
        'is_array': vt_info[2],
        'is_byref': vt_info[3],
    }

STANDARD_METHODS = {'QueryInterface', 'AddRef', 'Release', 'GetTypeInfoCount',
                    'GetTypeInfo', 'GetIDsOfNames', 'Invoke'}
INHERITED_FROM_OBJECT = {'Application', 'Database', 'Document', 'Handle',
                         'HasExtensionDictionary', 'ObjectID', 'ObjectName', 'OwnerID'}
INHERITED_METHODS_OBJECT = {'GetXData', 'SetXData', 'Delete', 'GetExtensionDictionary', 'Erase'}
INHERITED_FROM_ENTITY = INHERITED_FROM_OBJECT | {
    'EntityName', 'EntityTransparency', 'EntityType', 'Hyperlinks', 'Layer',
    'Linetype', 'LinetypeScale', 'Lineweight', 'Material', 'Normal',
    'PlotStyleName', 'TrueColor', 'Visible', 'color'}
INHERITED_METHODS_ENTITY = INHERITED_METHODS_OBJECT | {
    'ArrayPolar', 'ArrayRectangular', 'Highlight', 'Copy', 'Move',
    'Rotate', 'Rotate3D', 'Mirror', 'Mirror3D', 'ScaleEntity',
    'TransformBy', 'Update', 'GetBoundingBox', 'IntersectWith', 'Offset'}

# ====================================================================
# AutoCAD 类型库误标修正表
# 类型库中这些属性声明为 VT_I4 (int)，但运行时实际返回 Dispatch (COM 对象)。
# 如果不修正，生成的 Java 代码会调用 .getInt() 导致运行时异常：
#   "getInt() only legal on Variants of type VariantInt, not 9"
# ====================================================================
DISPATCH_MISTYPED_PROPS = {
    # COM 集合属性 — 返回 Collection Dispatch
    'Blocks', 'Dictionaries', 'DimStyles', 'Groups', 'Layers', 'Layouts',
    'Linetypes', 'Materials', 'ModelSpace', 'PaperSpace', 'PlotConfigurations',
    'RegisteredApplications', 'SectionManager', 'SummaryInfo', 'TextStyles',
    'UserCoordinateSystems', 'Viewports', 'Views', 'SelectionSets', 'MenuGroups',
    # COM 对象属性 — 返回 Object Dispatch
    'Application', 'Database', 'Document', 'Preferences', 'Plot',
    'TrueColor', 'Hyperlinks', 'BackgroundColor',
}

# AutoCAD ObjectName mapping (Acad class → AcDb name)
OBJECT_NAME_MAP = {
    'Acad3DFace': 'AcDbFace', 'Acad3DPolyline': 'AcDb3dPolyline',
    'Acad3DSolid': 'AcDb3dSolid', 'AcadArc': 'AcDbArc',
    'AcadAttribute': 'AcDbAttributeDefinition', 'AcadAttributeReference': 'AcDbAttribute',
    'AcadBlockReference': 'AcDbBlockReference', 'AcadCircle': 'AcDbCircle',
    # 标注的 ObjectName 是 ObjectARX 类名（AcDbAlignedDimension 等），不是接口名加前缀
    'AcadDimension': 'AcDbDimension', 'AcadDimAligned': 'AcDbAlignedDimension',
    'AcadDimAngular': 'AcDb2LineAngularDimension', 'AcadDimDiametric': 'AcDbDiametricDimension',
    'AcadDimOrdinate': 'AcDbOrdinateDimension', 'AcadDimRadial': 'AcDbRadialDimension',
    'AcadDimRotated': 'AcDbRotatedDimension', 'AcadDim3PointAngular': 'AcDb3PointAngularDimension',
    'AcadDimArcLength': 'AcDbArcDimension', 'AcadDimRadialLarge': 'AcDbRadialDimensionLarge',
    'AcadEllipse': 'AcDbEllipse', 'AcadHatch': 'AcDbHatch',
    'AcadLeader': 'AcDbLeader', 'AcadMLeader': 'AcDbMLeader',
    'AcadLine': 'AcDbLine', 'AcadMText': 'AcDbMText',
    'AcadPoint': 'AcDbPoint', 'AcadPolyline': 'AcDb2dPolyline',
    'AcadLWPolyline': 'AcDbPolyline', 'AcadPolygonMesh': 'AcDbPolygonMesh',
    'AcadPolyfaceMesh': 'AcDbPolyFaceMesh', 'AcadRay': 'AcDbRay',
    'AcadXline': 'AcDbXline', 'AcadShape': 'AcDbShape',
    'AcadSolid': 'AcDbSolid', 'AcadSpline': 'AcDbSpline',
    'AcadText': 'AcDbText', 'AcadTolerance': 'AcDbFcf',
    'AcadTrace': 'AcDbTrace', 'AcadRegion': 'AcDbRegion',
    'AcadRasterImage': 'AcDbRasterImage', 'AcadWipeout': 'AcDbWipeout',
    'AcadOle': 'AcDbOle2Frame', 'AcadHelix': 'AcDbHelix',
    'AcadSurface': 'AcDbSurface', 'AcadPlaneSurface': 'AcDbPlaneSurface',
    'AcadExtrudedSurface': 'AcDbExtrudedSurface',
    'AcadRevolvedSurface': 'AcDbRevolvedSurface',
    'AcadSweptSurface': 'AcDbSweptSurface',
    'AcadLoftedSurface': 'AcDbLoftedSurface',
    'AcadNurbSurface': 'AcDbNurbSurface',
    'AcadSection': 'AcDbSection', 'AcadSubDMesh': 'AcDbSubDMesh',
    'AcadExternalReference': 'AcDbBlockReference',
    'AcadComparedReference': 'AcDbBlockReference',
    'AcadMInsertBlock': 'AcDbMInsertBlock', 'AcadMLine': 'AcDbMline',
    'AcadTable': 'AcDbTable', 'AcadPViewport': 'AcDbViewport',
    'AcadDwfUnderlay': 'AcDbDwfReference',
    'AcadDgnUnderlay': 'AcDbDgnReference',
    'AcadPdfUnderlay': 'AcDbPdfReference',
    'AcadUnderlay': '',
    'AcadMLeader': 'AcDbMLeader',
    'AcadPointCloud': 'AcDbPointCloud',
    'AcadPointCloudEx': 'AcDbPointCloud',
    # 2024 点云的 ObjectName 是 AcDbPointCloudEx，COM 接口为 IAcadPointCloudEx2
    'AcadPointCloudEx2': 'AcDbPointCloudEx',
    'AcadSection': 'AcDbSection', 'AcadSection2': 'AcDbSection',
    'AcadSubDMesh': 'AcDbSubDMesh',
    'AcadGeoPositionMarker': 'AcDbGeoPositionMarker',
    'AcadGeomapImage': 'AcDbGeoMapImage',
    'AcadSubEntity': 'AcDbEntity',
}

# Entity inheritance (from AutoCAD docs, not from type lib which is flat)
ENTITY_PARENTS = {
    'AcadEntity': 'AcadObject',
    'Acad3DFace': 'AcadEntity', 'Acad3DPolyline': 'AcadEntity',
    'Acad3DSolid': 'AcadEntity', 'AcadArc': 'AcadEntity',
    'AcadAttribute': 'AcadEntity', 'AcadAttributeReference': 'AcadEntity',
    'AcadBlockReference': 'AcadEntity', 'AcadCircle': 'AcadEntity',
    'AcadDimension': 'AcadEntity', 'AcadEllipse': 'AcadEntity',
    'AcadHatch': 'AcadEntity', 'AcadLeader': 'AcadEntity',
    'AcadLine': 'AcadEntity', 'AcadMText': 'AcadEntity',
    'AcadPoint': 'AcadEntity', 'AcadPolyline': 'AcadEntity',
    'AcadLWPolyline': 'AcadEntity', 'AcadPolygonMesh': 'AcadEntity',
    'AcadPolyfaceMesh': 'AcadEntity', 'AcadRay': 'AcadEntity',
    'AcadShape': 'AcadEntity', 'AcadSolid': 'AcadEntity',
    'AcadSpline': 'AcadEntity', 'AcadText': 'AcadEntity',
    'AcadTolerance': 'AcadEntity', 'AcadTrace': 'AcadEntity',
    'AcadXline': 'AcadEntity', 'AcadRegion': 'AcadEntity',
    'AcadRasterImage': 'AcadEntity', 'AcadWipeout': 'AcadEntity',
    'AcadOle': 'AcadEntity', 'AcadHelix': 'AcadEntity',
    'AcadMLine': 'AcadEntity', 'AcadMInsertBlock': 'AcadEntity',
    'AcadTable': 'AcadEntity', 'AcadPViewport': 'AcadEntity',
    'AcadGeoPositionMarker': 'AcadEntity', 'AcadGeomapImage': 'AcadEntity',
    'AcadSubDMesh': 'AcadEntity',
    # Surfaces extend AcadSurface
    'AcadSurface': 'AcadEntity', 'AcadPlaneSurface': 'AcadSurface',
    'AcadExtrudedSurface': 'AcadSurface', 'AcadRevolvedSurface': 'AcadSurface',
    'AcadSweptSurface': 'AcadSurface', 'AcadLoftedSurface': 'AcadSurface',
    'AcadNurbSurface': 'AcadSurface',
    # Dimensions extend AcadDimension
    'AcadDimAligned': 'AcadDimension', 'AcadDimAngular': 'AcadDimension',
    'AcadDimDiametric': 'AcadDimension', 'AcadDimOrdinate': 'AcadDimension',
    'AcadDimRadial': 'AcadDimension', 'AcadDimRotated': 'AcadDimension',
    'AcadDim3PointAngular': 'AcadDimension', 'AcadDimArcLength': 'AcadDimension',
    'AcadDimRadialLarge': 'AcadDimension',
    # ExternalReference extends BlockReference
    'AcadExternalReference': 'AcadBlockReference',
    'AcadComparedReference': 'AcadBlockReference',
    # Underlays
    'AcadDwfUnderlay': 'AcadUnderlay', 'AcadDgnUnderlay': 'AcadUnderlay',
    'AcadPdfUnderlay': 'AcadUnderlay',
    'AcadUnderlay': 'AcadEntity',
    # MLeader
    'AcadMLeader': 'AcadEntity',
    # PointCloud
    'AcadPointCloud': 'AcadEntity', 'AcadPointCloudEx': 'AcadPointCloud',
    'AcadPointCloudEx2': 'AcadPointCloudEx',
    # Section
    'AcadSection': 'AcadEntity', 'AcadSection2': 'AcadSection',
}

results = {}

for i in range(tlb.GetTypeInfoCount()):
    try:
        name = tlb.GetDocumentation(i)[0]
        info = tlb.GetTypeInfo(i)
        attr = info.GetTypeAttr()
        if attr[5] != 4 or not name.startswith('IAcad'):
            continue

        props = {}
        methods = []
        _set_info(info)
        for j in range(attr[6]):
            try:
                fd = info.GetFuncDesc(j)
                memid = fd[0]
                inv = fd[4]
                names = info.GetNames(memid)
                fname = names[0] if names else f'func_{j}'
                if fname in STANDARD_METHODS:
                    continue

                # return type from fd[8]
                ret_info = extract_elemdesc(fd[8]) if len(fd) > 8 else {}

                # params from fd[2]
                params = []
                param_descs = fd[2] if len(fd) > 2 and fd[2] else ()
                if isinstance(param_descs, tuple):
                    for pidx, pdesc in enumerate(param_descs):
                        pinfo = extract_elemdesc(pdesc)
                        pname = names[pidx + 1] if pidx + 1 < len(names) else f'arg{pidx}'
                        params.append({
                            'name': pname,
                            'type': pinfo['java_type'],
                            'is_array': pinfo['is_array'],
                            'is_byref': pinfo['is_byref'],
                        })

                INV_MAP = {1: 'method', 2: 'get', 4: 'put', 8: 'putref'}
                ks = INV_MAP.get(inv, f'inv{inv}')
                if ks in ('get', 'put', 'putref'):
                    if fname not in props:
                        # 只写属性（无 propget）：类型取 put 的值参数
                        val = params[-1] if ks != 'get' and params else None
                        props[fname] = {'access': [],
                                        'ret_type': val['type'] if val else (ret_info.get('java_type') or 'Object'),
                                        'is_array': val['is_array'] if val else ret_info.get('is_array', False)}
                    if ks not in props[fname]['access']:
                        props[fname]['access'].append(ks)
                    # 带索引参数的属性（如 Coordinate(Index)）：put 的最后一个参数是值本身
                    n_index = len(params) - (1 if ks != 'get' else 0)
                    if n_index > 0:
                        props[fname]['index_params'] = params[:n_index]
                    if ks == 'get':
                        props[fname]['ret_type'] = ret_info.get('java_type', 'Object')
                        props[fname]['is_array'] = ret_info.get('is_array', False)
                else:
                    methods.append({
                        'name': fname,
                        'params': params,
                        'ret_type': ret_info.get('java_type', 'Object'),
                        'ret_is_array': ret_info.get('is_array', False),
                    })
            except:
                pass

        cls_name = name[1:]  # strip I
        results[cls_name] = {
            'parent': ENTITY_PARENTS.get(cls_name, ''),
            'object_name': OBJECT_NAME_MAP.get(cls_name, ''),
            'properties': [{'name': k, 'access': v['access'],
                            'type': 'Dispatch' if k in DISPATCH_MISTYPED_PROPS and v['ret_type'] == 'int' else v['ret_type'],
                            'is_array': v['is_array'],
                            **({'index_params': v['index_params']} if v.get('index_params') else {})}
                           for k, v in sorted(props.items())],
            'methods': methods,
        }
    except:
        pass

out = os.path.join(_HERE, 'acad_typelib.json')
with open(out, 'w', encoding='utf-8') as f:
    json.dump(results, f, ensure_ascii=False, indent=2)

# === Extract event interfaces (_IAcad*Events) ===
events = {}
for i in range(tlb.GetTypeInfoCount()):
    try:
        name = tlb.GetDocumentation(i)[0]
        info = tlb.GetTypeInfo(i)
        attr = info.GetTypeAttr()
        # Event interfaces: typekind 4 (dispatch) or 5 (coclass) with source flag
        # AutoCAD event interfaces are named _IAcad*Events
        if not name.startswith('_IAcad') and not name.endswith('Events'):
            continue

        ev_methods = []
        _set_info(info)
        for j in range(attr[6]):
            try:
                fd = info.GetFuncDesc(j)
                memid = fd[0]
                names = info.GetNames(memid)
                fname = names[0] if names else f'func_{j}'
                if fname in STANDARD_METHODS:
                    continue
                # params
                params = []
                param_descs = fd[2] if len(fd) > 2 and fd[2] else ()
                if isinstance(param_descs, tuple):
                    for pidx, pdesc in enumerate(param_descs):
                        pinfo = extract_elemdesc(pdesc)
                        pname = names[pidx + 1] if pidx + 1 < len(names) else f'arg{pidx}'
                        params.append({'name': pname, 'type': pinfo['java_type']})
                ev_methods.append({'name': fname, 'params': params})
            except:
                pass

        if ev_methods:
            # Strip leading _ and I
            ev_name = name.lstrip('_')
            # Remove 'Events' suffix for matching, store original
            events[ev_name] = ev_methods
    except:
        pass

events_out = os.path.join(_HERE, 'acad_events.json')
with open(events_out, 'w', encoding='utf-8') as f:
    json.dump(events, f, ensure_ascii=False, indent=2)

print(f"\nEvent interfaces: {len(events)}")
for n in sorted(events.keys()):
    print(f"  {n}: {len(events[n])} events")
    for ev in events[n]:
        params_str = ', '.join(f"{p['type']} {p['name']}" for p in ev['params'])
        print(f"    {ev['name']}({params_str})")

# Stats
entities = [n for n in results if ENTITY_PARENTS.get(n) and n != 'AcadObject']
print(f"Total: {len(results)} interfaces, {len(entities)} entity types")
for n in sorted(entities):
    r = results[n]
    print(f"  {n} (parent={r['parent']}, obj={r['object_name']}): {len(r['properties'])}p {len(r['methods'])}m")
