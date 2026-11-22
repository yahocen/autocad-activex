"""
Generate Java non-entity COM object classes from AutoCAD 2024 ActiveX type library.
- Collections (Count+Item) → document/manager/ extends ComCollection<T>
- Block-like (Count+Item+AddEntity*) → document/ extends ComCollection<AcadEntity>
- Plain AcadObject (has Handle/ObjectID) → document/manager/ extends AcadObject
- Non-AcadObject (no Handle/ObjectID) → document/manager/ extends ComObject
Skips interfaces that already have hand-written Java classes.
"""
import json, os, re, textwrap

_HERE = os.path.dirname(os.path.abspath(__file__))
_PROJECT = os.path.dirname(_HERE)
DATA = json.load(open(os.path.join(_HERE, 'acad_typelib.json'), 'r', encoding='utf-8'))

# ==================== Class name mapping ====================
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

# Existing hand-written classes — skip generation
SKIP_EXISTING = {
    'AcadApplication', 'AcadDocument', 'AcadDocuments', 'AcadModelSpace',
    'AcadUtility', 'AcadLayer', 'AcadLayers',
    'AcadSelectionSet', 'AcadSelectionSets',
    'AcadTextStyle', 'AcadTextStyles',
    'AcadDictionary',
    'AcadObject', 'AcadEntity',
}

# Collection item type mapping (collection_interface → item_java_class)
COLLECTION_ITEM_TYPE = {
    'AcadBlocks': 'Block', 'AcadDictionaries': 'Dictionary',
    'AcadDimStyles': 'DimStyle', 'AcadGroups': 'Group',
    'AcadHyperlinks': 'Hyperlink', 'AcadLayouts': 'Layout',
    'AcadLineTypes': 'LineType', 'AcadMaterials': 'Material',
    'AcadPlotConfigurations': 'PlotConfiguration',
    'AcadRegisteredApplications': 'RegisteredApplication',
    'AcadUCSs': 'UCS', 'AcadViewports': 'Viewport', 'AcadViews': 'View',
    'AcadPopupMenus': 'PopupMenu', 'AcadToolbars': 'Toolbar',
    'AcadMenuGroups': 'MenuGroup', 'AcadPopupMenu': 'PopupMenuItem',
    'AcadToolbar': 'ToolbarItem',
    'AcadGroup': 'AcadEntity', 'AcadBlock': 'AcadEntity',
    'AcadPaperSpace': 'AcadEntity',
    'AcadMenuBar': 'Variant',
    'AcadSectionManager': 'Section',
}

# Block-like interfaces (have AddLine/AddCircle/etc entity factory methods)
BLOCK_LIKE = {'AcadBlock', 'AcadPaperSpace'}

# Item type package mapping (for collections whose item type is in a different package)
ITEM_TYPE_PACKAGE = {
    'Block': 'io.github.yahocen.acad.document',
    'PaperSpace': 'io.github.yahocen.acad.document',
    'Section': 'io.github.yahocen.acad.document.element',
    'AcadEntity': 'io.github.yahocen.acad.document.base',
    'Variant': 'io.github.yahocen.jacob.com',
    'Dictionary': 'io.github.yahocen.acad.document.manager',  # same package, but explicit
}

# Properties provided by AcadObject base class as named methods (skip for AcadObject/ComCollection subclasses)
# AcadObject provides: getObjectId(), getObjectName(), getOwnerId(), getHandle(), hasExtensionDictionary()
#   getApplication(), getDatabase(), getDocument()
ACADOBJ_SKIP_PROPS = {
    'ObjectID', 'ObjectName', 'OwnerID', 'Handle', 'HasExtensionDictionary',
    'Application', 'Database', 'Document',
}
COMCOL_SKIP_PROPS = ACADOBJ_SKIP_PROPS | {'Count'}  # ComCollection also provides count()

# Methods provided by AcadObject base class as named methods (skip for AcadObject/ComCollection subclasses)
# AcadObject provides: delete(), getExtensionDictionary(), getObjectId(), getHandle(), getObjectName(),
#   getXData(), setXData(), erase(), etc.
# AcadObject also has call("MethodName") for generic invocation, but we generate named methods for clarity.
ACADOBJ_SKIP_METHODS = {
    'Delete',  # AcadObject.delete()
    'GetExtensionDictionary',  # AcadObject.getExtensionDictionary()
    'GetXData',   # AcadObject.getXData()
    'SetXData',   # AcadObject.setXData()
    'Erase',      # AcadObject.erase()
}
COMCOL_SKIP_METHODS = ACADOBJ_SKIP_METHODS | {'Item'}  # ComCollection also provides item()

# ==================== Type inference ====================
def infer_java_type(prop):
    ptype = prop.get('type', 'Variant')
    is_array = prop.get('is_array', False)

    if is_array:
        return 'SafeArray'
    type_map = {
        'BSTR': 'String', 'long': 'long', 'int': 'int', 'double': 'double',
        'String': 'String', 'boolean': 'boolean', 'Variant': 'Variant',
        'VARIANT_BOOL': 'boolean', 'VARIANT': 'Variant',
        'Dispatch': 'Dispatch', 'IDispatch': 'Dispatch', 'IUnknown': 'Dispatch',
        'SAFEARRAY': 'SafeArray',
    }
    return type_map.get(ptype, 'Variant')

def to_param_name(raw_name):
    n = raw_name[0].lower() + raw_name[1:] if raw_name else 'arg'
    JAVA_KW = {'int','double','boolean','string','class','object','return','case','default',
               'long','short','byte','char','void','new','this','super','static','final',
               'import','package','extends','implements','interface','enum','switch','for',
               'while','if','else','try','catch','finally','throw','throws','break',
               'continue','instanceof','null','true','false','abstract','native','private',
               'protected','public','synchronized','transient','volatile','strictfp','goto',
               'const','assert','float','do'}
    if n in JAVA_KW:
        n = n + '_'
    return n

def java_param(params):
    parts = []
    for p in params:
        ptype = p.get('type', 'Object')
        if ptype in ('Dispatch', 'Object'):
            ptype = 'Variant'
        if p.get('is_array') or p.get('is_byref'):
            ptype = 'Variant'
        pname = to_param_name(p['name'])
        parts.append(f'{ptype} {pname}')
    return ', '.join(parts)

def call_args(params):
    parts = []
    for p in params:
        pname = to_param_name(p['name'])
        parts.append(pname)
    return ', '.join(parts)

def return_expr(ptype, call_target):
    if ptype in ('String',):
        return f'{call_target}.getString()'
    elif ptype in ('int',):
        return f'{call_target}.getInt()'
    elif ptype in ('long',):
        return f'{call_target}.getLong()'
    elif ptype in ('double',):
        return f'{call_target}.getDouble()'
    elif ptype in ('boolean',):
        return f'{call_target}.getBoolean()'
    elif ptype in ('SafeArray',):
        return f'{call_target}.toSafeArray()'
    elif ptype in ('Dispatch',):
        return f'{call_target}.getDispatch()'
    else:
        return call_target

def gen_getter(prop_name, java_type, use_dispatch=False):
    method_name = 'get' + prop_name
    if use_dispatch:
        expr = f'Dispatch.get(dispatch, "{prop_name}")'
    else:
        expr = f'getProperties("{prop_name}")'
    if java_type == 'SafeArray':
        body = f'{expr}.toSafeArray()'
    elif java_type == 'Variant':
        body = expr
    elif java_type == 'Dispatch':
        body = f'{expr}.getDispatch()'
    elif java_type == 'String':
        body = f'{expr}.getString()'
    elif java_type == 'int':
        body = f'{expr}.getInt()'
    elif java_type == 'long':
        body = f'{expr}.getLong()'
    elif java_type == 'double':
        body = f'{expr}.getDouble()'
    elif java_type == 'boolean':
        body = f'{expr}.getBoolean()'
    else:
        body = expr
    return method_name, body

def gen_setter(prop_name, java_type, use_dispatch=False):
    method_name = 'set' + prop_name
    param_name = to_param_name(prop_name)
    if use_dispatch:
        body = f'Dispatch.put(dispatch, "{prop_name}", {param_name})'
    else:
        body = f'setProperties("{prop_name}", {param_name})'
    return method_name, body, param_name

# ==================== Class generator ====================
def gen_nonentity(cls_name, data):
    java_name = NONENTITY_CLASS_NAMES.get(cls_name, cls_name.replace('Acad', ''))
    props = data.get('properties', [])
    methods = data.get('methods', [])

    prop_names = {p['name'] for p in props}
    method_names = {m['name'] for m in methods}
    has_count = 'Count' in prop_names
    has_item = 'Item' in method_names
    has_handle = 'Handle' in prop_names or 'ObjectID' in prop_names
    is_block_like = cls_name in BLOCK_LIKE

    # Determine base class
    if is_block_like:
        base_class = 'ComCollection<AcadEntity>'
        base_import = 'io.github.yahocen.acad.common.ComCollection'
        item_type = 'AcadEntity'
        wrap_body = 'return EntityUtil.parse(dispatch);'
        extra_imports = [
            'io.github.yahocen.acad.document.base.AcadEntity',
            'io.github.yahocen.acad.util.EntityUtil',
        ]
    elif has_count and has_item:
        item_type = COLLECTION_ITEM_TYPE.get(cls_name, 'Variant')
        if item_type == 'AcadEntity':
            base_class = 'ComCollection<AcadEntity>'
            wrap_body = 'return EntityUtil.parse(dispatch);'
            extra_imports = [
                'io.github.yahocen.acad.document.base.AcadEntity',
                'io.github.yahocen.acad.util.EntityUtil',
            ]
        else:
            base_class = f'ComCollection<{item_type}>'
            wrap_body = f'return new {item_type}(dispatch);'
            extra_imports = []
            # Add import if item type is in a different package
            item_pkg = ITEM_TYPE_PACKAGE.get(item_type)
            if item_pkg:
                extra_imports.append(f'{item_pkg}.{item_type}')
        base_import = 'io.github.yahocen.acad.common.ComCollection'
    elif has_handle:
        base_class = 'AcadObject'
        base_import = 'io.github.yahocen.acad.document.base.AcadObject'
        extra_imports = []
    else:
        base_class = 'ComObject'
        base_import = 'io.github.yahocen.acad.common.ComObject'
        extra_imports = []

    # Determine which props/methods to skip based on base class
    if base_class.startswith('ComCollection'):
        skip_props = COMCOL_SKIP_PROPS
        skip_methods = COMCOL_SKIP_METHODS
    elif base_class == 'AcadObject':
        skip_props = ACADOBJ_SKIP_PROPS
        skip_methods = ACADOBJ_SKIP_METHODS
    else:  # ComObject — doesn't inherit AcadObject, don't skip anything
        skip_props = set()
        skip_methods = set()

    # Generate property getters/setters
    lines = []
    for prop in props:
        pname = prop['name']
        if pname in skip_props:
            continue
        java_type = infer_java_type(prop)
        use_dispatch = (base_class == 'ComObject')
        getter_name, getter_body = gen_getter(pname, java_type, use_dispatch)
        access = prop.get('access', [])

        # Getter
        if 'get' in access:
            lines.append(f'    public {java_type} {getter_name}() {{')
            lines.append(f'        return {getter_body};')
            lines.append(f'    }}')
            lines.append('')

        # Setter
        if 'put' in access:
            setter_name, setter_body, param_name = gen_setter(pname, java_type, use_dispatch)
            lines.append(f'    public {java_name} {setter_name}({java_type} {param_name}) {{')
            lines.append(f'        {setter_body};')
            lines.append(f'        return this;')
            lines.append(f'    }}')
            lines.append('')

    # Generate methods
    for method in methods:
        mname = method['name']
        if mname in skip_methods:
            continue
        if has_count and has_item and mname == 'Item':
            continue  # ComCollection provides item()
        # Note: 'Add' for collections is NOT skipped — each collection has unique Add params
        params = method.get('params', [])
        ret_type_raw = method.get('ret_type', 'Object')
        ret_java = infer_java_type({'type': ret_type_raw, 'is_array': method.get('ret_is_array', False)})

        param_str = java_param(params)
        arg_str = call_args(params)

        # Method name: lowercase first letter, handle Java keywords
        method_name = to_param_name(mname) if mname else 'call'

        if ret_java == 'Variant':
            ret_stmt = f'Variant result = Dispatch.call(dispatch, "{mname}"'
            if arg_str:
                ret_stmt += f', {arg_str}'
            ret_stmt += ');'
            lines.append(f'    public Variant {method_name}({param_str}) {{')
            lines.append(f'        {ret_stmt}')
            lines.append(f'        return result;')
            lines.append(f'    }}')
        else:
            call_expr = f'Dispatch.call(dispatch, "{mname}"'
            if arg_str:
                call_expr += f', {arg_str}'
            call_expr += ')'
            ret_val = return_expr(ret_java, call_expr)
            lines.append(f'    public {ret_java} {method_name}({param_str}) {{')
            lines.append(f'        return {ret_val};')
            lines.append(f'    }}')
        lines.append('')

    # Build class
    imports = set()
    imports.add('io.github.yahocen.jacob.com.Dispatch')
    imports.add('io.github.yahocen.jacob.com.Variant')
    imports.add(base_import)
    if base_class.startswith('ComCollection'):
        pass  # ComCollection import already added
    if 'SafeArray' in str(lines) or 'SafeArray' in base_class:
        imports.add('io.github.yahocen.jacob.com.SafeArray')
    for imp in extra_imports:
        imports.add(imp)

    # Determine package
    if is_block_like or java_name == 'PaperSpace':
        pkg = 'io.github.yahocen.acad.document'
        out_dir = os.path.join(_PROJECT, 'src', 'main', 'java', 'io', 'github', 'yahocen', 'acad', 'document')
    else:
        pkg = 'io.github.yahocen.acad.document.manager'
        out_dir = os.path.join(_PROJECT, 'src', 'main', 'java', 'io', 'github', 'yahocen', 'acad', 'document', 'manager')

    # Build file
    code = f'// Copyright 2026 Yahocen\n// SPDX-License-Identifier: Apache-2.0\npackage {pkg};\n\n'
    for imp in sorted(imports):
        code += f'import {imp};\n'
    code += '\n'
    code += f'/**\n'
    code += f' * {cls_name} — AutoCAD 2024 ActiveX COM object\n'
    code += f' * <p>Auto-generated from acax24enu.tlb type library.</p>\n'
    code += f' */\n'
    code += f'public class {java_name} extends {base_class} {{\n\n'
    code += f'    public {java_name}(Dispatch dispatch) {{\n'
    code += f'        super(dispatch);\n'
    code += f'    }}\n'

    # Add wrap() for collections
    if 'ComCollection' in base_class:
        code += '\n'
        code += f'    @Override\n'
        code += f'    protected {item_type} wrap(Dispatch dispatch) {{\n'
        code += f'        {wrap_body}\n'
        code += f'    }}\n'

    # Add generated members
    for line in lines:
        code += line + '\n'

    code += '}\n'

    return java_name, pkg, out_dir, code

# ==================== Main ====================
generated = 0
skipped = 0
errors = 0

for name, item in DATA.items():
    if not isinstance(item, dict):
        continue
    if name in SKIP_EXISTING:
        skipped += 1
        continue
    obj_name = item.get('object_name', '')
    if obj_name:
        continue  # entity, skip
    if name not in NONENTITY_CLASS_NAMES:
        continue

    try:
        java_name, pkg, out_dir, code = gen_nonentity(name, item)
        os.makedirs(out_dir, exist_ok=True)
        filepath = os.path.join(out_dir, f'{java_name}.java')

        # Overwrite existing files (force regeneration)
        # if os.path.exists(filepath):
        #     print(f'SKIP (exists): {java_name}.java')
        #     skipped += 1
        #     continue

        # Write without BOM
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(code)
        generated += 1
        print(f'GEN: {java_name}.java → {pkg}')
    except Exception as e:
        print(f'ERROR: {name} → {e}')
        errors += 1

print(f'\nGenerated: {generated}, Skipped: {skipped}, Errors: {errors}')
