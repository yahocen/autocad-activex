"""
Verify non-entity COM object classes against AutoCAD 2024 type library.
Checks: completeness, inheritance chain, property coverage, method coverage.
"""
import json, os, re

_HERE = os.path.dirname(os.path.abspath(__file__))
DATA = json.load(open(os.path.join(_HERE, 'acad_typelib.json'), 'r', encoding='utf-8'))

# ==================== Class name mapping (from gen_noneentities.py) ====================
NONENTITY_CLASS_NAMES = {
    'AcadAcCmColor': 'AcCmColor', 'AcadBlock': 'Block', 'AcadBlocks': 'Blocks',
    'AcadDatabase': 'Database', 'AcadDatabasePreferences': 'DatabasePreferences',
    'AcadDictionaries': 'Dictionaries',
    'AcadDimStyle': 'DimStyle', 'AcadDimStyles': 'DimStyles',
    'AcadDynamicBlockReferenceProperty': 'DynamicBlockReferenceProperty',
    'AcadGroup': 'Group', 'AcadGroups': 'Groups',
    'AcadHyperlink': 'Hyperlink', 'AcadHyperlinks': 'Hyperlinks',
    'AcadIdPair': 'IdPair',
    'AcadLayerStateManager': 'LayerStateManager',
    'AcadLayout': 'Layout', 'AcadLayouts': 'Layouts',
    'AcadLineType': 'LineType', 'AcadLineTypes': 'LineTypes',
    'AcadMLeaderLeader': 'MLeaderLeader', 'AcadMLeaderStyle': 'MLeaderStyle',
    'AcadMaterial': 'Material', 'AcadMaterials': 'Materials',
    'AcadMenuBar': 'MenuBar', 'AcadMenuGroup': 'MenuGroup', 'AcadMenuGroups': 'MenuGroups',
    'AcadPaperSpace': 'PaperSpace',
    'AcadPlot': 'Plot', 'AcadPlotConfiguration': 'PlotConfiguration',
    'AcadPlotConfigurations': 'PlotConfigurations',
    'AcadPopupMenu': 'PopupMenu', 'AcadPopupMenus': 'PopupMenus',
    'AcadPopupMenuItem': 'PopupMenuItem',
    'AcadPreferences': 'Preferences', 'AcadPreferencesDisplay': 'PreferencesDisplay',
    'AcadPreferencesDrafting': 'PreferencesDrafting', 'AcadPreferencesFiles': 'PreferencesFiles',
    'AcadPreferencesOpenSave': 'PreferencesOpenSave', 'AcadPreferencesOutput': 'PreferencesOutput',
    'AcadPreferencesProfiles': 'PreferencesProfiles',
    'AcadPreferencesSelection': 'PreferencesSelection',
    'AcadPreferencesSystem': 'PreferencesSystem', 'AcadPreferencesUser': 'PreferencesUser',
    'AcadRegisteredApplication': 'RegisteredApplication',
    'AcadRegisteredApplications': 'RegisteredApplications',
    'AcadSectionManager': 'SectionManager', 'AcadSectionSettings': 'SectionSettings',
    'AcadSectionTypeSettings': 'SectionTypeSettings',
    'AcadSectionTypeSettings2': 'SectionTypeSettings2',
    'AcadSecurityParams': 'SecurityParams',
    'AcadShadowDisplay': 'ShadowDisplay', 'AcadSortentsTable': 'SortentsTable',
    'AcadState': 'State',
    'AcadSubDMeshEdge': 'SubDMeshEdge', 'AcadSubDMeshFace': 'SubDMeshFace',
    'AcadSubDMeshVertex': 'SubDMeshVertex',
    'AcadSubEntSolidEdge': 'SubEntSolidEdge', 'AcadSubEntSolidFace': 'SubEntSolidFace',
    'AcadSubEntSolidNode': 'SubEntSolidNode', 'AcadSubEntSolidVertex': 'SubEntSolidVertex',
    'AcadSubEntity': 'SubEntity',
    'AcadSummaryInfo': 'SummaryInfo', 'AcadTableStyle': 'TableStyle',
    'AcadToolbar': 'Toolbar', 'AcadToolbarItem': 'ToolbarItem', 'AcadToolbars': 'Toolbars',
    'AcadUCS': 'UCS', 'AcadUCSs': 'UCSs',
    'AcadView': 'View', 'AcadViewports': 'Viewports', 'AcadViews': 'Views',
    'AcadViewport': 'Viewport',
    'AcadXRecord': 'XRecord',
}

# Existing hand-written classes — not generated, skip in coverage checks
SKIP_EXISTING = {
    'AcadApplication', 'AcadDocument', 'AcadDocuments', 'AcadModelSpace',
    'AcadUtility', 'AcadLayer', 'AcadLayers',
    'AcadSelectionSet', 'AcadSelectionSets',
    'AcadTextStyle', 'AcadTextStyles',
    'AcadDictionary',
    'AcadObject', 'AcadEntity',
}

# Non-AcadObject interfaces (extend ComObject instead of AcadObject)
NON_ACAD_OBJECT = {
    'AcadPreferences', 'AcadPreferencesDisplay', 'AcadPreferencesDrafting',
    'AcadPreferencesFiles', 'AcadPreferencesOpenSave', 'AcadPreferencesOutput',
    'AcadPreferencesProfiles', 'AcadPreferencesSelection', 'AcadPreferencesSystem',
    'AcadPreferencesUser', 'AcadAcCmColor', 'AcadSubEntity',
    'AcadSubDMeshEdge', 'AcadSubDMeshFace', 'AcadSubDMeshVertex',
    'AcadSubEntSolidEdge', 'AcadSubEntSolidFace', 'AcadSubEntSolidNode',
    'AcadSubEntSolidVertex', 'AcadHyperlink', 'AcadHyperlinks',
    'AcadIdPair', 'AcadSecurityParams', 'AcadShadowDisplay',
    'AcadDynamicBlockReferenceProperty', 'AcadMLeaderLeader',
    'AcadDatabasePreferences', 'AcadSummaryInfo',
}

# Collection interfaces (have Count + Item)
COLLECTIONS = {
    'AcadBlocks', 'AcadDictionaries', 'AcadDimStyles', 'AcadGroups',
    'AcadHyperlinks', 'AcadLayouts', 'AcadLineTypes', 'AcadMaterials',
    'AcadPlotConfigurations', 'AcadRegisteredApplications',
    'AcadUCSs', 'AcadViewports', 'AcadViews',
    'AcadPopupMenus', 'AcadToolbars', 'AcadMenuGroups',
    'AcadMenuBar', 'AcadSectionManager',
    'AcadGroup', 'AcadBlock', 'AcadPaperSpace',
    'AcadPopupMenu', 'AcadToolbar',
}

# Block-like (have AddLine etc)
BLOCK_LIKE = {'AcadBlock', 'AcadPaperSpace'}

manager_dir = os.path.join(_HERE, '..', 'src', 'main', 'java', 'io', 'github', 'yahocen', 'acad', 'document', 'manager')
document_dir = os.path.join(_HERE, '..', 'src', 'main', 'java', 'io', 'github', 'yahocen', 'acad', 'document')

# Collect all Java files in manager/ and document/
java_files = {}
for d in [manager_dir, document_dir]:
    for f in os.listdir(d):
        if f.endswith('.java'):
            java_files[f[:-5]] = os.path.join(d, f)

# All non-entity interfaces from type library (no object_name, starts with Acad)
tlb_nonentities = {}
for name, item in DATA.items():
    if not isinstance(item, dict):
        continue
    if item.get('object_name'):
        continue  # entity
    if name in SKIP_EXISTING:
        continue
    if name not in NONENTITY_CLASS_NAMES:
        continue
    tlb_nonentities[name] = item

def get_extends(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    m = re.search(r'class\s+\w+\s+extends\s+(\w+(?:<\w+>)?)', content)
    return m.group(1) if m else None

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

JAVA_KW = {'int','double','boolean','string','class','object','return','case','default',
           'long','short','byte','char','void','new','this','super','static','final',
           'import','package','extends','implements','interface','enum','switch','for',
           'while','if','else','try','catch','finally','throw','throws','break',
           'continue','instanceof','null','true','false','abstract','native','private',
           'protected','public','synchronized','transient','volatile','strictfp','goto',
           'const','assert','float','do'}

def to_java_method_name(raw):
    n = raw[0].lower() + raw[1:] if raw else ''
    if n in JAVA_KW:
        return n + '_'
    return n

# ==================== CHECK 1: Completeness ====================
print("=" * 80)
print("CHECK 1: Type library non-entity interfaces → Java class exists?")
print("=" * 80)
missing = []
for tlb_name in sorted(tlb_nonentities):
    java_name = NONENTITY_CLASS_NAMES[tlb_name]
    if java_name not in java_files:
        missing.append((tlb_name, java_name))
        print(f"  MISSING: {tlb_name} → {java_name}.java")
if not missing:
    print(f"  All {len(tlb_nonentities)} non-entity interfaces have Java classes ✓")

# ==================== CHECK 2: Java classes → Type library ====================
print()
print("=" * 80)
print("CHECK 2: Non-entity Java classes → Type library mapping?")
print("=" * 80)
reverse_map = {v: k for k, v in NONENTITY_CLASS_NAMES.items()}
extra = []
for jf in sorted(java_files):
    # Skip hand-written classes
    if jf in {'Application','Document','Documents','ModelSpace','Utility',
              'Layer','Layers','Dictionary','SelectionSet','SelectionSets',
              'TextStyle','TextStyles','AcadObject','AcadEntity','Point',
              'ComObject','ComCollection','AcadEntityName','AcadException',
              'AcadUtil','AcRegenType','AlignmentEnum','AutoCadVersion',
              'ColorIndex','LineDirection','LineWeight','PointUtil',
              'EventSink','ApplicationEventSink','DocumentEventSink','ObjectEventSink'}:
        continue
    if jf not in reverse_map:
        extra.append(jf)
        print(f"  EXTRA: {jf}.java (no type library mapping)")
if not extra:
    print(f"  All non-entity Java classes have type library mappings ✓")

# ==================== CHECK 3: Base class correctness ====================
print()
print("=" * 80)
print("CHECK 3: Base class (ComCollection vs AcadObject vs ComObject)")
print("=" * 80)
issues = []
for tlb_name in sorted(tlb_nonentities):
    java_name = NONENTITY_CLASS_NAMES[tlb_name]
    filepath = java_files.get(java_name)
    if not filepath:
        continue
    
    props = {p['name'] for p in tlb_nonentities[tlb_name].get('properties', [])}
    methods = {m['name'] for m in tlb_nonentities[tlb_name].get('methods', [])}
    has_count = 'Count' in props
    has_item = 'Item' in methods
    has_handle = 'Handle' in props or 'ObjectID' in props
    is_block_like = tlb_name in BLOCK_LIKE
    
    if is_block_like:
        expected_base = 'ComCollection<AcadEntity>'
    elif has_count and has_item:
        expected_base = 'ComCollection<'  # starts with ComCollection
    elif tlb_name in NON_ACAD_OBJECT:
        expected_base = 'ComObject'
    elif has_handle:
        expected_base = 'AcadObject'
    else:
        expected_base = 'ComObject'
    
    actual_base = get_extends(filepath)
    
    if expected_base == 'ComCollection<':
        if not actual_base or not actual_base.startswith('ComCollection'):
            issues.append((java_name, expected_base, actual_base))
            print(f"  MISMATCH: {java_name} extends {actual_base} (expected ComCollection<?>)")
    else:
        if actual_base != expected_base:
            issues.append((java_name, expected_base, actual_base))
            print(f"  MISMATCH: {java_name} extends {actual_base} (expected {expected_base})")

if not issues:
    print(f"  All {len(tlb_nonentities)} classes have correct base classes ✓")

# ==================== CHECK 4: Property coverage ====================
print()
print("=" * 80)
print("CHECK 4: Property coverage (new properties not inherited)")
print("=" * 80)

# Properties provided by base classes
COMCOL_PROPS = {'Count', 'Item'}  # ComCollection provides count(), item()
ACADOBJ_PROPS = {'Application', 'Database', 'Document', 'Handle', 'HasExtensionDictionary',
                 'ObjectID', 'ObjectName', 'OwnerID'}
ACADOBJ_METHODS = {'GetXData', 'SetXData', 'Delete', 'GetExtensionDictionary', 'Erase', 'Item'}

issues = []
for tlb_name in sorted(tlb_nonentities):
    java_name = NONENTITY_CLASS_NAMES[tlb_name]
    filepath = java_files.get(java_name)
    if not filepath:
        continue
    
    item = tlb_nonentities[tlb_name]
    all_props = {p['name'] for p in item.get('properties', [])}
    parent = item.get('parent', '')
    inherited = get_all_inherited_props(parent) if parent else set()
    
    # Also subtract base class props depending on base
    actual_base = get_extends(filepath)
    if actual_base and actual_base.startswith('ComCollection'):
        inherited |= ACADOBJ_PROPS | COMCOL_PROPS
    elif actual_base == 'AcadObject':
        inherited |= ACADOBJ_PROPS
    
    new_props = all_props - inherited
    
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find getters
    java_getters = set(re.findall(r'public \w+ get(\w+)\(', content))
    java_bools = set(re.findall(r'public boolean is(\w+)\(', content))
    java_getters |= java_bools
    direct_access = set(re.findall(r'(?:getProperties|Dispatch\.get)\(dispatch,\s*"(\w+)"\)', content))
    # Also Dispatch.get(dispatch, "Xxx") pattern — already covered above
    
    missing = []
    for prop in new_props:
        if prop in java_getters or prop in direct_access:
            continue
        # Check setter only (write-only property)
        if re.search(rf'(?:setProperties|Dispatch\.put)\(dispatch,\s*"{prop}"', content):
            continue
        # Check if it's accessed via getProperties("Prop")
        if f'"{prop}"' in content:
            continue
        missing.append(prop)
    
    if missing:
        issues.append((java_name, missing))
        print(f"  {java_name}: MISSING {len(missing)} props: {missing}")

if not issues:
    print(f"  All new properties have getters/setters ✓")

# ==================== CHECK 5: Method coverage ====================
print()
print("=" * 80)
print("CHECK 5: Method coverage (new methods not inherited)")
print("=" * 80)

issues = []
for tlb_name in sorted(tlb_nonentities):
    java_name = NONENTITY_CLASS_NAMES[tlb_name]
    filepath = java_files.get(java_name)
    if not filepath:
        continue
    
    item = tlb_nonentities[tlb_name]
    all_methods = {m['name'] for m in item.get('methods', [])}
    parent = item.get('parent', '')
    inherited = get_all_inherited_methods(parent) if parent else set()
    
    actual_base = get_extends(filepath)
    if actual_base and actual_base.startswith('ComCollection'):
        inherited |= ACADOBJ_METHODS | {'Item'}
    elif actual_base == 'AcadObject':
        inherited |= ACADOBJ_METHODS
    
    new_methods = all_methods - inherited
    
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()
    
    # Find all public method names
    java_methods = set(re.findall(r'public (?:static )?[\w.]+(?:<[\w<>, ?]+>)?(?:\[\])? (\w+)\(', content))
    java_methods |= set(re.findall(r'public void (\w+)\(', content))
    
    missing = []
    for method in new_methods:
        java_mname = to_java_method_name(method)
        if java_mname not in java_methods:
            # Check if method is called via Dispatch.call(dispatch, "MethodName")
            if f'"{method}"' in content:
                continue
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
print(f"  Type library non-entity interfaces: {len(tlb_nonentities)}")
print(f"  Java non-entity classes found:      {len([f for f in java_files if f in reverse_map])}")
print(f"  Missing Java classes:               {len(missing) if 'missing' in dir() else '?'}")
print(f"  Extra Java classes:                 {len(extra)}")
print(f"  Base class mismatches:              {len(issues) if 'issues' in dir() else '?'}")
