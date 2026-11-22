"""
Generate Java entity + event sink classes from AutoCAD 2024 ActiveX type library.
- Entity classes → document/element/
- Non-entity COM object classes → document/manager/ (collections) or document/ (objects)
- Event sink classes → event/
"""
import json, os, re

_HERE = os.path.dirname(os.path.abspath(__file__))
_PROJECT = os.path.dirname(_HERE)
DATA = json.load(open(os.path.join(_HERE, 'acad_typelib.json'), 'r', encoding='utf-8'))
EVENTS = {}
try:
    EVENTS = json.load(open(os.path.join(_HERE, 'acad_events.json'), 'r', encoding='utf-8'))
except:
    pass

# ==================== Entity class name mapping ====================
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
    # New entities
    'AcadMLeader': 'MLeader',
    'AcadPointCloud': 'PointCloud', 'AcadPointCloudEx': 'PointCloudEx',
    'AcadPointCloudEx2': 'PointCloudEx2',
    'AcadSection': 'Section', 'AcadSection2': 'Section2',
}

# Non-entity COM object class name mapping
NONENTITY_CLASS_NAMES = {
    'AcadAcCmColor': 'AcCmColor', 'AcadApplication': 'Application',
    'AcadBlock': 'Block', 'AcadBlocks': 'Blocks',
    'AcadDatabase': 'Database', 'AcadDatabasePreferences': 'DatabasePreferences',
    'AcadDictionaries': 'Dictionaries', 'AcadDictionary': 'DictionaryEntry',
    'AcadDimStyle': 'DimStyle', 'AcadDimStyles': 'DimStyles',
    'AcadDocument': 'DocumentEntry', 'AcadDocuments': 'DocumentsEntry',
    'AcadDynamicBlockReferenceProperty': 'DynamicBlockReferenceProperty',
    'AcadGroup': 'Group', 'AcadGroups': 'Groups',
    'AcadHyperlink': 'Hyperlink', 'AcadHyperlinks': 'Hyperlinks',
    'AcadIdPair': 'IdPair',
    'AcadLayer': 'LayerEntry', 'AcadLayers': 'LayersEntry',
    'AcadLayerStateManager': 'LayerStateManager',
    'AcadLayout': 'Layout', 'AcadLayouts': 'Layouts',
    'AcadLineType': 'LineType', 'AcadLineTypes': 'LineTypes',
    'AcadMLeaderLeader': 'MLeaderLeader', 'AcadMLeaderStyle': 'MLeaderStyle',
    'AcadMaterial': 'Material', 'AcadMaterials': 'Materials',
    'AcadMenuBar': 'MenuBar', 'AcadMenuGroup': 'MenuGroup', 'AcadMenuGroups': 'MenuGroups',
    'AcadModelSpace': 'ModelSpaceEntry', 'AcadPaperSpace': 'PaperSpace',
    'AcadPlot': 'Plot', 'AcadPlotConfiguration': 'PlotConfiguration',
    'AcadPlotConfigurations': 'PlotConfigurations',
    'AcadPointCloud': 'PointCloud',
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
    'AcadSelectionSet': 'SelectionSetEntry', 'AcadSelectionSets': 'SelectionSetsEntry',
    'AcadShadowDisplay': 'ShadowDisplay',
    'AcadSortentsTable': 'SortentsTable', 'AcadState': 'State',
    'AcadSubDMeshEdge': 'SubDMeshEdge', 'AcadSubDMeshFace': 'SubDMeshFace',
    'AcadSubDMeshVertex': 'SubDMeshVertex',
    'AcadSubEntSolidEdge': 'SubEntSolidEdge', 'AcadSubEntSolidFace': 'SubEntSolidFace',
    'AcadSubEntSolidNode': 'SubEntSolidNode', 'AcadSubEntSolidVertex': 'SubEntSolidVertex',
    'AcadSubEntity': 'SubEntity',
    'AcadSummaryInfo': 'SummaryInfo',
    'AcadTableStyle': 'TableStyle', 'AcadTextStyle': 'TextStyleEntry',
    'AcadTextStyles': 'TextStylesEntry',
    'AcadToolbar': 'Toolbar', 'AcadToolbarItem': 'ToolbarItem', 'AcadToolbars': 'Toolbars',
    'AcadUCS': 'UCS', 'AcadUCSs': 'UCSs',
    'AcadUtility': 'UtilityEntry',
    'AcadView': 'View', 'AcadViewport': 'Viewport', 'AcadViewports': 'Viewports',
    'AcadViews': 'Views', 'AcadXRecord': 'XRecord',
}

# Merge — skip non-entities that already have hand-written Java classes
SKIP_EXISTING = {
    # Already hand-written, don't overwrite
    'AcadApplication', 'AcadDocument', 'AcadDocuments', 'AcadModelSpace',
    'AcadUtility', 'AcadLayer', 'AcadLayers', 'AcadSelectionSet', 'AcadSelectionSets',
    'AcadTextStyle', 'AcadTextStyles', 'AcadDictionary',
    'AcadObject', 'AcadEntity',
}

# Java parent class mapping for entity hierarchy
PARENT_JAVA = {
    'AcadObject': 'AcadObject', 'AcadEntity': 'AcadEntity', 'AcadDimension': 'Dimension',
    'AcadBlockReference': 'BlockReference', 'AcadSurface': 'Surface',
    'AcadUnderlay': 'Underlay', 'AcadPointCloud': 'PointCloud', 'AcadSection': 'Section',
}

# ==================== Inherited members ====================
INH_OBJECT_PROPS = {'Application', 'Database', 'Document', 'Handle', 'HasExtensionDictionary',
                    'ObjectID', 'ObjectName', 'OwnerID'}
INH_OBJECT_METHODS = {'GetXData', 'SetXData', 'Delete', 'GetExtensionDictionary', 'Erase'}
INH_ENTITY_PROPS = INH_OBJECT_PROPS | {
    'EntityName', 'EntityTransparency', 'EntityType', 'Hyperlinks', 'Layer', 'Linetype',
    'LinetypeScale', 'Lineweight', 'Material', 'PlotStyleName', 'TrueColor',
    'Visible', 'color'}
# Note: 'Normal' is NOT in AcadEntity — each entity defines it independently.
# Do NOT add it here, or generated classes will miss getNormal()/setNormal().
INH_ENTITY_METHODS = INH_OBJECT_METHODS | {
    'ArrayPolar', 'ArrayRectangular', 'Highlight', 'Copy', 'Move', 'Rotate', 'Rotate3D',
    'Mirror', 'Mirror3D', 'ScaleEntity', 'TransformBy', 'Update', 'GetBoundingBox',
    'IntersectWith'}
# Note: 'Offset' is NOT in AcadEntity — only on Curve entities (Line/Circle/Arc/Ellipse/LWPolyline).
# Do NOT add it here, or generated classes will miss offset() method.

# ==================== Type inference ====================
def infer_prop_type(prop_name, com_type):
    n = prop_name.lower()
    # 点坐标在类型库中都是 VARIANT（double 数组）；AttachmentPoint 这类同名后缀的是枚举，不能当点
    if com_type not in ('', 'Variant', 'Object'):
        pass
    elif n.endswith('point') or n in ('origin', 'center', 'insertionpoint', 'textalignmentpoint',
                                     'basepoint', 'firstpoint', 'secondpoint', 'centerpoint',
                                     'endpoint', 'startpoint', 'pickedpoint', 'originpoint'):
        return 'Point'
    if n == 'normal':
        return 'Variant'
    # extract_tlb.py 已展开别名/枚举/接口，类型库给出确定类型时以它为准；
    # 下面的按名称猜测只用于 VARIANT 类属性（旧版按名称猜测曾把 *transparency* 猜成 String、
    # *color* 猜成 int，而它们实际是 boolean / IAcadAcCmColor）
    if com_type in ('String', 'double', 'int', 'long', 'boolean', 'Dispatch'):
        return com_type
    if n in ('layer', 'linetype', 'material', 'plotstylename'):
        return 'String'
    if 'name' in n and 'object' not in n:
        return 'String'
    if n in ('handle', 'textstring', 'text', 'content', 'prompt', 'tagstring'):
        return 'String'
    if any(k in n for k in ('scale', 'angle', 'length', 'radius', 'area', 'height', 'width', 'thickness')):
        return 'double'
    if 'visible' in n:
        return 'boolean'
    # COM 类型库修正后的 Dispatch 类型优先（TrueColor, Hyperlinks, Database 等）
    if com_type == 'Dispatch':
        return 'Dispatch'
    if any(k in n for k in ('count', 'index', 'color', 'lineweight')):
        return 'int'
    if 'transparency' in n:
        return 'String'
    if com_type == 'String': return 'String'
    if com_type == 'double': return 'double'
    if com_type == 'int': return 'int'
    if com_type == 'boolean': return 'boolean'
    return 'Variant'

def get_getter_expr(prop_name, java_type):
    if java_type == 'Point':
        return f'Point.of(getProperties("{prop_name}").toSafeArray())'
    if java_type == 'String': return f'getProperties("{prop_name}").getString()'
    if java_type == 'double': return f'getProperties("{prop_name}").getDouble()'
    if java_type == 'int': return f'getProperties("{prop_name}").getInt()'
    if java_type == 'long': return f'getProperties("{prop_name}").getLong()'
    if java_type == 'boolean': return f'getProperties("{prop_name}").getBoolean()'
    if java_type == 'Dispatch': return f'getProperties("{prop_name}").getDispatch()'
    return f'getProperties("{prop_name}")'

def get_setter_expr(prop_name, java_type, param_name):
    if java_type == 'Point':
        return f'setProperties("{prop_name}", {param_name}.toVariant())'
    return f'setProperties("{prop_name}", {param_name})'

def to_param_name(raw_name):
    n = raw_name[0].lower() + raw_name[1:] if raw_name else 'arg'
    JAVA_KW = {'int','double','boolean','string','class','object','return','case','default',
               'long','short','byte','char','void','new','this','super','static','final',
               'import','package','extends','implements','interface','enum','switch','for',
               'while','if','else','try','catch','finally','throw','throws','break',
               'continue','instanceof','null','true','false','abstract','native','private',
               'protected','public','synchronized','transient','volatile','strictfp','goto',
               'const','assert'}
    if n in JAVA_KW:
        n = n + '_'
    return n

def java_param(params):
    parts = []
    for p in params:
        ptype = p.get('type', 'Object')
        if ptype in ('Dispatch', 'Object'):
            ptype = 'Variant'
        if p.get('is_array'):
            ptype = 'Variant'
        pname = to_param_name(p['name'])
        parts.append(f'{ptype} {pname}')
    return ', '.join(parts)

def call_args(params):
    parts = []
    for p in params:
        ptype = p.get('type', 'Object')
        if ptype in ('Dispatch', 'Object'):
            ptype = 'Variant'
        if p.get('is_array'):
            ptype = 'Variant'
        pname = to_param_name(p['name'])
        if ptype == 'Point':
            parts.append(f'{pname}.toVariant()')
        else:
            parts.append(pname)
    return ', '.join(parts)

# ==================== Entity class generator ====================
def gen_entity(cls_name, data):
    java_name = CLASS_NAMES.get(cls_name, cls_name.replace('Acad', ''))
    parent = data.get('parent', '')
    parent_java = CLASS_NAMES.get(parent, PARENT_JAVA.get(parent, 'AcadEntity'))
    object_name = data.get('object_name', '')

    # Determine inherited sets
    if parent == 'AcadObject':
        inh_props, inh_methods = set(), set()
    elif parent in ('AcadEntity', 'AcadUnderlay', 'AcadSurface', 'AcadPointCloud', 'AcadSection'):
        inh_props, inh_methods = INH_ENTITY_PROPS.copy(), INH_ENTITY_METHODS.copy()
    elif parent == 'AcadDimension':
        inh_props = INH_ENTITY_PROPS.copy() | INH_DIM_PROPS
        inh_methods = INH_ENTITY_METHODS.copy() | INH_DIM_METHODS
    elif parent == 'AcadBlockReference':
        inh_props = INH_ENTITY_PROPS.copy() | INH_BLOCKREF_PROPS
        inh_methods = INH_ENTITY_METHODS.copy() | INH_BLOCKREF_METHODS
    else:
        inh_props, inh_methods = INH_ENTITY_PROPS.copy(), INH_ENTITY_METHODS.copy()

    # Also subtract parent's own members
    if parent in DATA:
        pdata = DATA[parent]
        inh_props |= {p['name'] for p in pdata.get('properties', [])}
        inh_methods |= {m['name'] for m in pdata.get('methods', [])}

    new_props = [p for p in data['properties'] if p['name'] not in inh_props]
    new_methods = [m for m in data['methods'] if m['name'] not in inh_methods]

    lines = []
    lines.append('// Copyright 2026 Yahocen')
    lines.append('// SPDX-License-Identifier: Apache-2.0')
    lines.append('package io.github.yahocen.acad.document.element;')
    lines.append('')
    lines.append('import io.github.yahocen.jacob.com.Dispatch;')
    lines.append('import io.github.yahocen.jacob.com.Variant;')
    if any(infer_prop_type(p['name'], p.get('type', '')) == 'Point' for p in new_props):
        lines.append('import io.github.yahocen.acad.document.base.Point;')
    lines.append('import io.github.yahocen.acad.common.AcadEntityName;')
    # Parent import — AcadEntity is in base package, always needs explicit import
    if parent in CLASS_NAMES and CLASS_NAMES[parent] != 'AcadEntity':
        parent_java_name = CLASS_NAMES[parent]
        if parent_java_name != java_name:
            lines.append(f'import io.github.yahocen.acad.document.element.{parent_java_name};')
    lines.append('import io.github.yahocen.acad.document.base.AcadEntity;')
    lines.append('')
    lines.append(f'/**')
    lines.append(f' * {java_name} - AutoCAD {java_name} 实体')
    lines.append(f' */')
    # Only generate @AcadEntityName if this object_name is unique to this class
    # (not inherited from parent). Subclasses with same ObjectName would conflict
    # in EntityUtil.parse().
    parent_obj_name = ''
    if parent in DATA:
        parent_obj_name = DATA[parent].get('object_name', '')
    if object_name and object_name != parent_obj_name:
        lines.append(f'@AcadEntityName("{object_name}")')
    lines.append(f'public class {java_name} extends {parent_java} {{')
    lines.append('')
    lines.append(f'    public {java_name}(Dispatch dispatch) {{')
    lines.append(f'        super(dispatch);')
    lines.append(f'    }}')
    lines.append('')

    # Properties
    for prop in new_props:
        pname = prop['name']
        access = prop['access']
        jtype = infer_prop_type(pname, prop.get('type', ''))
        getter = 'get' in access
        setter = 'put' in access or 'putref' in access

        # 带索引参数的属性（如 Coordinate(Index)）：不能无参读写，生成 get/setXxx(int index, ...)
        if prop.get('index_params'):
            idx = ', '.join(f"int {to_param_name(ip['name'])}" for ip in prop['index_params'])
            idx_args = ', '.join(to_param_name(ip['name']) for ip in prop['index_params'])
            if getter:
                lines.append(f'    public Variant get{pname}({idx}) {{')
                lines.append(f'        return getIndexedProperty("{pname}", {idx_args});')
                lines.append(f'    }}')
                lines.append('')
            if setter:
                lines.append(f'    public {java_name} set{pname}({idx}, Variant value) {{')
                lines.append(f'        setIndexedProperty("{pname}", value, {idx_args});')
                lines.append(f'        return this;')
                lines.append(f'    }}')
                lines.append('')
            continue

        if getter:
            expr = get_getter_expr(pname, jtype)
            lines.append(f'    public {jtype} get{pname}() {{')
            lines.append(f'        return {expr};')
            lines.append(f'    }}')
            lines.append('')
        if setter:
            param = to_param_name(pname)
            expr = get_setter_expr(pname, jtype, param)
            lines.append(f'    public {java_name} set{pname}({jtype} {param}) {{')
            lines.append(f'        {expr};')
            lines.append(f'        return this;')
            lines.append(f'    }}')
            lines.append('')

    # Methods
    JAVA_KW = {'boolean','int','double','long','short','byte','char','void','class','return',
               'case','default','new','this','super','static','final','import','package',
               'extends','implements','interface','enum','switch','for','while','if','else',
               'try','catch','finally','throw','throws','break','continue','instanceof',
               'null','true','false','abstract','native','private','protected','public',
               'synchronized','transient','volatile','strictfp','goto','const','assert'}
    for method in new_methods:
        mname = method['name']
        params = method.get('params', [])
        ret_type = method.get('ret_type', 'Object')
        if ret_type == 'Dispatch': ret_type = 'Variant'
        if ret_type in ('None', None): ret_type = 'void'
        if ret_type == 'Object': ret_type = 'Variant'

        java_mname = mname[0].lower() + mname[1:]
        if java_mname in JAVA_KW:
            java_mname = java_mname + 'Op'

        param_decl = java_param(params)
        arg_exprs = call_args(params)
        if params:
            call_expr = f'call("{mname}", {arg_exprs})'
        else:
            call_expr = f'call("{mname}")'

        if ret_type == 'void':
            lines.append(f'    public void {java_mname}({param_decl}) {{')
            lines.append(f'        {call_expr};')
            lines.append(f'    }}')
        elif ret_type == 'Variant':
            lines.append(f'    public Variant {java_mname}({param_decl}) {{')
            lines.append(f'        return {call_expr};')
            lines.append(f'    }}')
        elif ret_type == 'int':
            lines.append(f'    public int {java_mname}({param_decl}) {{')
            lines.append(f'        return {call_expr}.getInt();')
            lines.append(f'    }}')
        elif ret_type == 'double':
            lines.append(f'    public double {java_mname}({param_decl}) {{')
            lines.append(f'        return {call_expr}.getDouble();')
            lines.append(f'    }}')
        elif ret_type == 'boolean':
            lines.append(f'    public boolean {java_mname}({param_decl}) {{')
            lines.append(f'        return {call_expr}.getBoolean();')
            lines.append(f'    }}')
        elif ret_type == 'String':
            lines.append(f'    public String {java_mname}({param_decl}) {{')
            lines.append(f'        return {call_expr}.getString();')
            lines.append(f'    }}')
        else:
            lines.append(f'    public Variant {java_mname}({param_decl}) {{')
            lines.append(f'        return {call_expr};')
            lines.append(f'    }}')
        lines.append('')

    lines.append('}')
    return '\n'.join(lines)

# ==================== Event sink generator ====================
def gen_event_sink(event_name, event_methods):
    """Generate a Java EventSink class for a COM event interface."""
    # Map event interface names to Java class names
    EVENT_SINK_NAMES = {
        'DAcadApplicationEvents': 'ApplicationEventSink',
        'DAcadDocumentEvents': 'DocumentEventSink',
        'IAcadObjectEvents': 'ObjectEventSink',
    }
    java_name = EVENT_SINK_NAMES.get(event_name, event_name.replace('Events', 'EventSink'))

    lines = []
    lines.append('// Copyright 2026 Yahocen')
    lines.append('// SPDX-License-Identifier: Apache-2.0')
    lines.append('package io.github.yahocen.acad.event;')
    lines.append('')
    lines.append('import io.github.yahocen.jacob.com.Variant;')
    lines.append('')
    lines.append(f'/**')
    lines.append(f' * {java_name} - AutoCAD {event_name} 事件接收器')
    lines.append(f' * <p>覆盖方法以响应对应事件。所有方法默认空实现。</p>')
    lines.append(f' */')
    lines.append(f'public abstract class {java_name} extends EventSink {{')
    lines.append('')

    for ev in event_methods:
        mname = ev['name']
        params = ev.get('params', [])
        # All event params come as Variant[] in JACOB DispatchEvents
        lines.append(f'    /**')
        lines.append(f'     * {mname} 事件')
        if params:
            param_descs = []
            for p in params:
                param_descs.append(f'{p.get("type", "Object")} {p["name"]}')
            lines.append(f'     * 参数: {", ".join(param_descs)}')
        lines.append(f'     */')
        lines.append(f'    public void {mname}(Variant[] args) {{}}')
        lines.append('')

    lines.append('}')
    return '\n'.join(lines)

# ==================== Main generation ====================
# Inherited members for intermediate classes
dim_data = DATA.get('AcadDimension', {})
INH_DIM_PROPS = {p['name'] for p in dim_data.get('properties', [])}
INH_DIM_METHODS = {m['name'] for m in dim_data.get('methods', [])}
br_data = DATA.get('AcadBlockReference', {})
INH_BLOCKREF_PROPS = {p['name'] for p in br_data.get('properties', [])}
INH_BLOCKREF_METHODS = {m['name'] for m in br_data.get('methods', [])}

# --- Task 1: Generate entity classes ---
out_dir = os.path.join(_PROJECT, 'src', 'main', 'java', 'io', 'github', 'yahocen', 'acad', 'document', 'element')
os.makedirs(out_dir, exist_ok=True)

GENERATE_ENTITIES = [n for n in sorted(DATA.keys())
                     if DATA[n].get('parent') and n not in ('AcadEntity', 'AcadObject')]

generated = 0
skipped = 0
for cls_name in GENERATE_ENTITIES:
    java_name = CLASS_NAMES.get(cls_name)
    if not java_name:
        print(f"  SKIP {cls_name} (no name mapping)")
        skipped += 1
        continue
    code = gen_entity(cls_name, DATA[cls_name])
    out_path = os.path.join(out_dir, f'{java_name}.java')
    with open(out_path, 'w', encoding='utf-8') as f:
        f.write(code)
    generated += 1

print(f"Entity classes: generated={generated}, skipped={skipped}")

# --- Task 2: Generate event sink classes ---
event_dir = os.path.join(_PROJECT, 'src', 'main', 'java', 'io', 'github', 'yahocen', 'acad', 'event')
os.makedirs(event_dir, exist_ok=True)

ev_generated = 0
for ev_name, ev_methods in sorted(EVENTS.items()):
    code = gen_event_sink(ev_name, ev_methods)
    # Determine Java class name
    EVENT_SINK_NAMES = {
        'DAcadApplicationEvents': 'ApplicationEventSink',
        'DAcadDocumentEvents': 'DocumentEventSink',
        'IAcadObjectEvents': 'ObjectEventSink',
    }
    java_name = EVENT_SINK_NAMES.get(ev_name, ev_name.replace('Events', 'EventSink'))
    out_path = os.path.join(event_dir, f'{java_name}.java')
    with open(out_path, 'w', encoding='utf-8') as f:
        f.write(code)
    ev_generated += 1
    print(f"  Event sink: {java_name} ({len(ev_methods)} events)")

print(f"Event sinks: generated={ev_generated}")

# --- Task 3: Report convenience class conflicts ---
print("\n=== Convenience class @AcadEntityName conflicts ===")
conflicts = {}
for f in os.listdir(out_dir):
    if not f.endswith('.java'): continue
    fpath = os.path.join(out_dir, f)
    content = open(fpath, 'r', encoding='utf-8').read()
    for m in re.finditer(r'@AcadEntityName\("([^"]+)"\)', content):
        obj = m.group(1)
        conflicts.setdefault(obj, []).append(f.replace('.java', ''))
for obj, classes in sorted(conflicts.items()):
    if len(classes) > 1:
        print(f"  CONFLICT: {obj} → {classes}")
