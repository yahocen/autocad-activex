"""
Comprehensive entity mapping verification against AutoCAD 2024 type library.
Checks: completeness, inheritance chain, @AcadEntityName, property/method coverage.
"""
import json, os, re

_HERE = os.path.dirname(os.path.abspath(__file__))
DATA = json.load(open(os.path.join(_HERE, 'acad_typelib.json'), 'r', encoding='utf-8'))

# ==================== Class name mapping (from gen_entities.py) ====================
CLASS_NAMES = {
    'Acad3DFace': 'Face3D', 'Acad3DPolyline': 'Polyline3D', 'Acad3DSolid': 'Solid3D',
    'AcadArc': 'Arc', 'AcadAttribute': 'Attribute', 'AcadAttributeReference': 'AttributeReference',
    'AcadBlockReference': 'BlockReference', 'AcadCircle': 'Circle',
    'AcadComparedReference': 'ComparedReference',
    'AcadDimension': 'Dimension', 'AcadDim3PointAngular': 'Dim3PointAngular',
    'AcadDimAligned': 'DimAligned', 'AcadDimAngular': 'DimAngular',
    'AcadDimArcLength': 'DimArcLength', 'AcadDimDiametric': 'DimDiametric',
    'AcadDimOrdinate': 'DimOrdinate', 'AcadDimRadial': 'DimRadial',
    'AcadDimRadialLarge': 'DimRadialLarge', 'AcadDimRotated': 'DimRotated',
    'AcadDwfUnderlay': 'DwfUnderlay', 'AcadDgnUnderlay': 'DgnUnderlay',
    'AcadPdfUnderlay': 'PdfUnderlay', 'AcadUnderlay': 'Underlay',
    'AcadEllipse': 'Ellipse',
    'AcadEntity': 'AcadEntity', 'AcadExternalReference': 'ExternalReference',
    'AcadExtrudedSurface': 'ExtrudedSurface', 'AcadGeoPositionMarker': 'GeoPositionMarker',
    'AcadGeomapImage': 'GeomapImage', 'AcadHatch': 'Hatch', 'AcadHelix': 'Helix',
    'AcadLWPolyline': 'LWPolyline', 'AcadLeader': 'Leader', 'AcadLine': 'Line',
    'AcadLoftedSurface': 'LoftedSurface', 'AcadMInsertBlock': 'MInsertBlock',
    'AcadMLine': 'MLine', 'AcadMText': 'MText', 'AcadNurbSurface': 'NurbSurface',
    'AcadOle': 'Ole', 'AcadPViewport': 'PViewport', 'AcadPlaneSurface': 'PlaneSurface',
    'AcadPoint': 'PointEntity', 'AcadPolyfaceMesh': 'PolyfaceMesh', 'AcadPolygonMesh': 'PolygonMesh',
    'AcadPolyline': 'Polyline', 'AcadRasterImage': 'RasterImage', 'AcadRay': 'Ray',
    'AcadRegion': 'Region', 'AcadRevolvedSurface': 'RevolvedSurface', 'AcadShape': 'Shape',
    'AcadSolid': 'Solid2D', 'AcadSpline': 'Spline', 'AcadSubDMesh': 'SubDMesh',
    'AcadSurface': 'Surface', 'AcadSweptSurface': 'SweptSurface', 'AcadTable': 'Table',
    'AcadText': 'Text', 'AcadTolerance': 'Tolerance', 'AcadTrace': 'Trace',
    'AcadWipeout': 'Wipeout', 'AcadXline': 'XLine',
    'AcadMLeader': 'MLeader',
    'AcadPointCloud': 'PointCloud', 'AcadPointCloudEx': 'PointCloudEx',
    'AcadPointCloudEx2': 'PointCloudEx2',
    'AcadSection': 'Section', 'AcadSection2': 'Section2',
    'AcadSubEntity': 'SubEntity',
}

CONVENIENCE = {'Box', 'Cone', 'Cylinder', 'Sphere', 'Torus', 'Rectangle'}
# 类型库里没有独立接口、但运行时有独立 ObjectName 的实体（COM 上都实现 IAcadUnderlay），AutoCAD 2024 实测：
# PDF 底图 = AcDbPdfReference，DGN 底图 = AcDbDgnReference
RUNTIME_ONLY = {'PdfUnderlay', 'DgnUnderlay'}
CONVENIENCE = CONVENIENCE | RUNTIME_ONLY

element_dir = os.path.join(_HERE, '..', 'src', 'main', 'java', 'io', 'github', 'yahocen', 'acad', 'document', 'element')
java_files = set()
for f in os.listdir(element_dir):
    if f.endswith('.java'):
        java_files.add(f[:-5])

# Build inheritance set for a given type (all ancestor properties)
def get_all_inherited_props(tlb_name, seen=None):
    if seen is None:
        seen = set()
    if tlb_name in seen or tlb_name not in DATA:
        return set()
    seen.add(tlb_name)
    item = DATA[tlb_name]
    if not isinstance(item, dict):
        return set()
    props = {p['name'] for p in item.get('properties', [])}
    parent = item.get('parent', '')
    if parent:
        props |= get_all_inherited_props(parent, seen)
    return props

def get_all_inherited_methods(tlb_name, seen=None):
    if seen is None:
        seen = set()
    if tlb_name in seen or tlb_name not in DATA:
        return set()
    seen.add(tlb_name)
    item = DATA[tlb_name]
    if not isinstance(item, dict):
        return set()
    methods = {m['name'] for m in item.get('methods', [])}
    parent = item.get('parent', '')
    if parent:
        methods |= get_all_inherited_methods(parent, seen)
    return methods

def get_extends(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    m = re.search(r'class\s+\w+\s+extends\s+(\w+)', content)
    return m.group(1) if m else None

# ==================== CHECK 1: Completeness ====================
print("=" * 80)
print("CHECK 1: Type library entity types → Java class exists?")
print("=" * 80)

# Entity = has object_name (concrete drawable entity)
# Also include abstract base classes (AcadEntity, AcadUnderlay, AcadSurface, etc.) that have Java classes
tlb_entities = {}
tlb_abstract_bases = {}
for name, item in DATA.items():
    if not isinstance(item, dict):
        continue
    if item.get('object_name'):
        tlb_entities[name] = item
    elif name in CLASS_NAMES and name.startswith('Acad') and CLASS_NAMES.get(name) in java_files:
        tlb_abstract_bases[name] = item

missing = []
for tlb_name in sorted(tlb_entities):
    if tlb_name == 'AcadSubEntity':
        continue  # 子实体引用基类，非可绘图实体，封装于 manager/SubEntity.java（不在 element 目录）
    java_name = CLASS_NAMES.get(tlb_name, tlb_name.replace('Acad', ''))
    if java_name not in java_files:
        missing.append((tlb_name, java_name))
        print(f"  MISSING: {tlb_name} → {java_name}.java")

# SubEntity is special — object_name=AcDbEntity but NOT a drawable entity; wrapped in manager package
if 'AcadSubEntity' in tlb_entities:
    print("  NOTE: AcadSubEntity (ObjectName=AcDbEntity) 是子实体引用基类，非可绘图实体，封装于 manager/SubEntity.java")

if not missing:
    print(f"  All {len(tlb_entities)} concrete entity types have Java classes ✓")

# ==================== CHECK 2: Java classes → Type library mapping ====================
print()
print("=" * 80)
print("CHECK 2: Java classes → Type library entity or abstract base?")
print("=" * 80)
extra = []
for jf in sorted(java_files):
    if jf in CONVENIENCE:
        continue
    found = False
    for tlb_name, java_name in CLASS_NAMES.items():
        if java_name == jf and tlb_name in DATA:
            found = True
            break
    if not found:
        extra.append(jf)
        print(f"  EXTRA: {jf}.java (no matching type library entry)")

if not extra:
    print(f"  All Java classes have type library mappings ✓ (excluding {len(CONVENIENCE)} convenience classes)")

# ==================== CHECK 3: Inheritance chain ====================
print()
print("=" * 80)
print("CHECK 3: Inheritance chain (type library parent → Java extends)")
print("=" * 80)

PARENT_JAVA = {
    'AcadObject': 'AcadObject', 'AcadEntity': 'AcadEntity',
    'AcadDimension': 'Dimension', 'AcadBlockReference': 'BlockReference',
    'AcadSurface': 'Surface', 'AcadUnderlay': 'Underlay',
    'AcadPointCloud': 'PointCloud', 'AcadSection': 'Section',
}

issues = []
for tlb_name in sorted(list(tlb_entities) + list(tlb_abstract_bases)):
    java_name = CLASS_NAMES.get(tlb_name, tlb_name.replace('Acad', ''))
    filepath = os.path.join(element_dir, f'{java_name}.java')
    if not os.path.exists(filepath):
        continue
    
    tlb_parent = DATA[tlb_name].get('parent', '') if isinstance(DATA.get(tlb_name), dict) else ''
    expected_parent = CLASS_NAMES.get(tlb_parent, PARENT_JAVA.get(tlb_parent, ''))
    actual_parent = get_extends(filepath)
    
    if actual_parent != expected_parent:
        issues.append((java_name, tlb_parent, expected_parent, actual_parent))
        print(f"  MISMATCH: {java_name} extends {actual_parent} (expected {expected_parent} from tlb parent={tlb_parent})")

if not issues:
    print(f"  All {len(tlb_entities) + len(tlb_abstract_bases)} classes have correct inheritance ✓")

# ==================== CHECK 4: @AcadEntityName ====================
print()
print("=" * 80)
print("CHECK 4: @AcadEntityName annotation correctness")
print("=" * 80)

issues = []
for tlb_name in sorted(tlb_entities):
    if tlb_name == 'AcadSubEntity':
        continue  # Not generated
    java_name = CLASS_NAMES.get(tlb_name)
    filepath = os.path.join(element_dir, f'{java_name}.java')
    if not os.path.exists(filepath):
        continue
    
    expected_obj = DATA[tlb_name].get('object_name', '')
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    m = re.search(r'@AcadEntityName\("([^"]+)"\)', content)
    if m:
        actual_obj = m.group(1)
        if actual_obj != expected_obj:
            issues.append((java_name, expected_obj, actual_obj))
            print(f"  MISMATCH: {java_name} annotation=\"{actual_obj}\" vs tlb=\"{expected_obj}\"")
    else:
        # Check if sub-class shares parent's ObjectName (expected: no annotation)
        parent = DATA[tlb_name].get('parent', '')
        parent_obj = tlb_entities.get(parent, {}).get('object_name', '')
        if expected_obj == parent_obj and parent:
            pass  # Correct: no annotation for sub-class sharing parent ObjectName
        elif tlb_name in ('AcadEntity', 'AcadUnderlay', 'AcadSurface', 'AcadDimension', 'AcadBlockReference', 'AcadPointCloud', 'AcadSection'):
            pass  # Abstract base, no annotation expected
        else:
            issues.append((java_name, expected_obj, 'MISSING'))
            print(f"  MISSING: {java_name} has no @AcadEntityName (expected \"{expected_obj}\")")

if not issues:
    print(f"  All @AcadEntityName annotations correct ✓")

# ==================== CHECK 5: Property coverage (NEW props only) ====================
print()
print("=" * 80)
print("CHECK 5: Property coverage (new properties not inherited from ancestors)")
print("=" * 80)

# Collect ALL ancestor properties for each entity
issues = []
for tlb_name in sorted(tlb_entities):
    if tlb_name in ('AcadSubEntity', 'AcadEntity'):
        continue
    java_name = CLASS_NAMES.get(tlb_name)
    filepath = os.path.join(element_dir, f'{java_name}.java')
    if not os.path.exists(filepath):
        continue
    
    all_props = {p['name'] for p in DATA[tlb_name].get('properties', [])}
    inherited_props = get_all_inherited_props(DATA[tlb_name].get('parent', ''))
    new_props = all_props - inherited_props
    
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find all getter methods
    java_getters = set(re.findall(r'public \w+ get(\w+)\(\)', content))
    java_bools = set(re.findall(r'public boolean is(\w+)\(\)', content))
    java_getters |= java_bools
    
    # Also check for method-based access (getProperties("Xxx"))
    direct_access = set(re.findall(r'getProperties\("(\w+)"\)', content))
    
    missing = []
    for prop in new_props:
        # Check if there's a getter, or direct getProperties access
        if prop not in java_getters and prop not in direct_access:
            # Some properties only have setters (write-only)
            setter_check = re.search(rf'set{prop}\(', content)
            if not setter_check:
                missing.append(prop)
    
    if missing:
        issues.append((java_name, missing))
        print(f"  {java_name}: MISSING {len(missing)} props: {missing}")

if not issues:
    print(f"  All new properties have getters/setters ✓")

# ==================== CHECK 6: Method coverage ====================
print()
print("=" * 80)
print("CHECK 6: Method coverage (new methods not inherited from ancestors)")
print("=" * 80)

issues = []
for tlb_name in sorted(tlb_entities):
    if tlb_name in ('AcadSubEntity', 'AcadEntity'):
        continue
    java_name = CLASS_NAMES.get(tlb_name)
    filepath = os.path.join(element_dir, f'{java_name}.java')
    if not os.path.exists(filepath):
        continue
    
    all_methods = {m['name'] for m in DATA[tlb_name].get('methods', [])}
    inherited_methods = get_all_inherited_methods(DATA[tlb_name].get('parent', ''))
    new_methods = all_methods - inherited_methods
    
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find all public method names (any return type)
    java_methods = set(re.findall(r'public (?:static )?[\w.]+(?:<[\w<>, ?]+>)?(?:\[\])? (\w+)\(', content))
    # Do NOT filter out getters/setters — COM type library distinguishes properties from methods,
    # and methods like GetText/SetText are real methods, not property accessors.
    
    missing = []
    for method in new_methods:
        # Match gen_entities.py logic: lowercase first letter, then keyword escaping with 'Op' suffix
        method_lower = method[0].lower() + method[1:] if method else ''
        JAVA_KW = {'int','double','boolean','string','class','object','return','case','default',
                   'long','short','byte','char','void','new','this','super','static','final',
                   'import','package','extends','implements','interface','enum','switch','for',
                   'while','if','else','try','catch','finally','throw','throws','break',
                   'continue','instanceof','null','true','false','abstract','native','private',
                   'protected','public','synchronized','transient','volatile','strictfp','goto',
                   'const','assert','float','do'}
        if method_lower in JAVA_KW:
            method_kw = method_lower + 'Op'  # gen_entities.py uses 'Op' suffix
        else:
            method_kw = method_lower
        
        if method_kw not in java_methods:
            missing.append(method)
    
    if missing:
        issues.append((java_name, missing))
        print(f"  {java_name}: MISSING {len(missing)} methods: {missing}")

if not issues:
    print(f"  All new methods have Java wrappers ✓")

# ==================== SUMMARY ====================
print()
print("=" * 80)
print("SUMMARY")
print("=" * 80)
print(f"  Type library entity types (with ObjectName): {len(tlb_entities)}")
print(f"  Abstract base classes with Java impl:        {len(tlb_abstract_bases)}")
print(f"  Java element classes (total):                {len(java_files)}")
print(f"    - From type library:                       {len(java_files) - len(CONVENIENCE)}")
print(f"    - Convenience wrappers:                    {len(CONVENIENCE)}")
print(f"  Inheritance mismatches:                      {len(issues) if 'issues' in dir() else '?'}")
