// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.document.base;

import io.github.yahocen.acad.common.Internal;
import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.SafeArray;
import io.github.yahocen.jacob.com.Variant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.Application;
import io.github.yahocen.acad.common.ComObject;
import io.github.yahocen.acad.common.Disp;
import io.github.yahocen.acad.document.Document;
import io.github.yahocen.acad.document.manager.Database;
import io.github.yahocen.acad.document.manager.Dictionary;
import io.github.yahocen.acad.util.JacobUtil;

/**
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-1C457D0A-9933-4A74-B3E9-A7F3483B3F34">IAcadObject Interface (ActiveX)</a>
 * 
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "AcadObject AutoCAD对象基类",
    description = "AutoCAD ActiveX对象的基类，提供对象的基本操作"
)
public class AcadObject extends ComObject {

    public AcadObject(Dispatch dispatch) {
        super(dispatch);
    }

    /** 缓存所属文档引用，避免每次 getDocument() 都跨进程查询并新建对象。 */
    private Document cachedDocument;

    @Override
    public void safeRelease() {
        // 不释放 cachedDocument：getDocument() 已把同一实例交给调用方持有，这里释放会让
        // 对方拿到死引用（doc = entity.getDocument(); entity.safeRelease(); doc.xxx() 崩）。
        // 缓存引用随本对象置空丢弃即可，其底层 Dispatch 的释放由各持有方自行负责——
        // 与改动前"每次 getDocument() 新建包装、调用方自负"的生命周期语义一致。
        cachedDocument = null;
        super.safeRelease();
    }

    /**
     * 泛型包装实例（裸 {@code AcadObject} / {@code AcadEntity}，来自 copy/mirror/mirror3D、
     * Utility.toEntity、EntityUtil.parse 兜底等路径）会包装异构 COM 接口：同一个缓存桶
     * 先解析谁，后来同名成员就拿到谁的 DISPID，跨接口必然串号。这类实例的动态
     * 属性/方法调用必须回退按名解析（每调用多一次 GetIDsOfNames 往返，换取正确性）；
     * 具体子类（Line/Text/…）一类对一接口，仍然走缓存快路径。
     */
    private boolean isGenericWrapper() {
        var c = getClass();
        return c == AcadObject.class || c == AcadEntity.class;
    }

    /**
     * 获取对象属性值
     *
     * @param name 属性名称
     * @return 属性值
     */
    @Operation(summary = "获取对象属性值", description = "获取对象属性值")
    public Variant getProperties(@Parameter(description = "属性名称") String name) {
        return isGenericWrapper() ? Dispatch.get(dispatch, name) : Disp.get(getClass(), dispatch, name);
    }

    @Operation(summary = "设置对象属性值", description = "设置对象属性值")
    public void setProperties(@Parameter(description = "属性名称") String name, @Parameter(description = "属性值") Object value) {
        if (isGenericWrapper()) {
            Dispatch.put(dispatch, name, JacobUtil.toVariant(value));
        } else {
            Disp.put(getClass(), dispatch, name, JacobUtil.toVariant(value));
        }
    }

    /**
     * 读取带索引参数的属性，如 {@code Coordinate(Index)}。
     * <p>
     * 这类属性不能用 {@link #getProperties(String)} 读取（COM 报“无效的参数数目”）。
     * </p>
     *
     * @param name  属性名称
     * @param index 索引参数
     * @return 属性值
     */
    @Operation(summary = "获取带索引的属性值", description = "读取带索引参数的属性，如 Coordinate(Index)")
    public Variant getIndexedProperty(@Parameter(description = "属性名称") String name,
                                      @Parameter(description = "索引参数") Object... index) {
        Object[] args = new Object[index.length];
        for (int i = 0; i < index.length; i++) {
            args[i] = JacobUtil.toVariant(index[i]);
        }
        return isGenericWrapper()
                ? Dispatch.invoke(dispatch, name, Dispatch.Get, args, new int[args.length])
                : Disp.invoke(getClass(), dispatch, name, Dispatch.Get, args, new int[args.length]);
    }

    /**
     * 写入带索引参数的属性，如 {@code Coordinate(Index) = point}。
     *
     * @param name  属性名称
     * @param value 属性值
     * @param index 索引参数
     */
    @Operation(summary = "设置带索引的属性值", description = "写入带索引参数的属性，如 Coordinate(Index)")
    public void setIndexedProperty(@Parameter(description = "属性名称") String name,
                                   @Parameter(description = "属性值") Object value,
                                   @Parameter(description = "索引参数") Object... index) {
        Object[] args = new Object[index.length + 1];
        for (int i = 0; i < index.length; i++) {
            args[i] = JacobUtil.toVariant(index[i]);
        }
        args[index.length] = JacobUtil.toVariant(value);
        if (isGenericWrapper()) {
            Dispatch.invoke(dispatch, name, Dispatch.Put, args, new int[args.length]);
        } else {
            Disp.invoke(getClass(), dispatch, name, Dispatch.Put, args, new int[args.length]);
        }
    }

    @Operation(summary = "调用对象方法", description = "调用对象方法")
    public Variant call(@Parameter(description = "方法名") String methodName, @Parameter(description = "方法参数") Object... attributes) {
        if (isGenericWrapper()) {
            if (Internal.isEmpty(attributes)) {
                return Dispatch.call(dispatch, methodName);
            }
            return Dispatch.callN(dispatch, methodName, attributes);
        }
        if(Internal.isEmpty(attributes)) {
            return Disp.call(getClass(), dispatch, methodName);
        }
        return Disp.callN(getClass(), dispatch, methodName, attributes);
    }

    /**
     * 获取对象的唯一 ID
     * 
     * @return 对象ID
     */
    @Operation(summary = "获取对象的唯一 ID", description = "获取对象的唯一 ID")
    public long getObjectId() {
        return getProperties("ObjectID").getLong();
    }

    /**
     * 获取对象的名称
     * 
     * @return 对象名称
     */
    @Operation(summary = "获取对象的名称", description = "获取对象的名称")
    public String getObjectName() {
        return getProperties("ObjectName").getString();
    }

    /**
     * 获取所有者（父）对象的对象 ID
     * 
     * @return 所有者对象ID
     */
    @Operation(summary = "获取所有者（父）对象的对象 ID", description = "获取所有者（父）对象的对象 ID")
    public long getOwnerId() {
        return getProperties("OwnerID").getLong();
    }

    /**
     * 获取对象的句柄
     * 
     * @return 对象句柄
     */
    @Operation(summary = "获取对象的句柄", description = "获取对象的句柄")
    public String getHandle() {
        return getProperties("Handle").getString();
    }

    /**
     * 判断对象是否有关联的扩展字典。
     * 
     * @return 是否有关联的扩展字典
     */
    @Operation(summary = "判断对象是否有关联的扩展字典", description = "判断对象是否有关联的扩展字典")
    public boolean hasExtensionDictionary() {
        return getProperties("HasExtensionDictionary").getBoolean();
    }

    /**
     * 获取与对象关联的扩展字典。
     * 如果一个对象没有扩展字典，此方法将为该对象创建一个新的扩展字典，并在返回值中返回它。
     * 您可以通过使用 HasExtensionDictionary 属性查询对象是否具有扩展字典。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-87411ABA-ABCF-45AF-A8C5-0ED036A34D5F">GetExtensionDictionary Method (ActiveX)</a>
     * 
     * @return 扩展字典对象
     */
    @Operation(summary = "获取与对象关联的扩展字典", description = "获取与对象关联的扩展字典。如果一个对象没有扩展字典，此方法将为该对象创建一个新的扩展字典，并在返回值中返回它")
    public Dictionary getExtensionDictionary() {
        return new Dictionary(getProperties("GetExtensionDictionary").getDispatch());
    }

    /**
     * 删除指定的对象或一组已保存的图层设置。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-167FBA5F-C3F3-4195-A170-02E5E46EC797">Delete Method (ActiveX)</a>
     */
    @Operation(summary = "删除指定的对象", description = "删除指定的对象或一组已保存的图层设置")
    public void delete() {
        call("Delete");
    }

    /**
     * 获取对象所属的文档。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-51E19B6A-7A0F-4E3A-9C6E-3D3C3C3C3C3C">Document Property (ActiveX)</a>
     *
     * @return 文档对象
     */
    @Operation(summary = "获取对象所属的文档", description = "获取对象所属的文档")
    public Document getDocument() {
        if (cachedDocument == null) {
            cachedDocument = new Document(Disp.get(getClass(), this.dispatch, "Document").getDispatch());
        }
        return cachedDocument;
    }

    /**
     * 获取对象所属的 AutoCAD 应用程序对象。
     *
     * @return 应用程序对象
     */
    @Operation(summary = "获取对象所属的应用程序", description = "获取对象所属的 AutoCAD 应用程序对象")
    public Application getApplication() {
        return new Application(Disp.get(getClass(), this.dispatch,"Application").getDispatch());
    }

    /**
     * 获取对象所属的数据库。
     *
     * @return 数据库对象
     */
    @Operation(summary = "获取对象所属的数据库", description = "获取对象所属的数据库对象")
    public Database getDatabase() {
        return new Database(Disp.get(getClass(), this.dispatch,"Database").getDispatch());
    }

    /**
     * 擦除（从图形中移除）对象。
     * <p>
     * 与 {@link #delete()} 不同，Erase 仅将对象从图形中移除，
     * 对象仍可被重新添加；而 Delete 会彻底删除对象。
     * </p>
     */
    @Operation(summary = "擦除对象", description = "从图形中擦除该对象")
    public void erase() {
        call("Erase");
    }

    /**
     * 获取与对象关联的、指定应用程序的扩展数据 (XData)。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-347C5C7F-1F00-4B3F-8A4B-2A5A8B3B5B5B">GetXData Method (ActiveX)</a>
     *
     * @param appName 应用程序名称
     * @return 扩展数据（数据类型数组与数据值数组）
     */
    @Operation(summary = "获取对象的扩展数据", description = "获取与对象关联的指定应用程序的扩展数据 (XData)")
    public XData getXData(@Parameter(description = "应用程序名称") String appName) {
        var dataTypeHolder = new Variant();
        var dataTypeVar = new Variant();
        dataTypeVar.putVariantRef(dataTypeHolder);
        var dataValueHolder = new Variant();
        var dataValueVar = new Variant();
        dataValueVar.putVariantRef(dataValueHolder);
        var appNameVar = new Variant(appName);

        Disp.invokev(getClass(), dispatch, "GetXData", Dispatch.Method,
                new Variant[]{appNameVar, dataTypeVar, dataValueVar}, new int[3]);

        SafeArray typeArray = dataTypeHolder.toSafeArray();
        short[] dataType = typeArray.toShortArray();
        typeArray.safeRelease();
        SafeArray valueArray = dataValueHolder.toSafeArray();
        Variant[] dataValue = valueArray.toVariantArray();
        valueArray.safeRelease();

        appNameVar.safeRelease();
        dataTypeVar.safeRelease();
        dataValueVar.safeRelease();
        return new XData(dataType, dataValue);
    }

    /**
     * 设置与对象关联的扩展数据 (XData)。
     * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-2D52CC32-8C30-4C44-9E2E-6B3D3D3D3D3D">SetXData Method (ActiveX)</a>
     *
     * @param dataType 扩展数据类型数组（short 型整数代码）
     * @param dataValue 扩展数据值数组
     */
    @Operation(summary = "设置对象的扩展数据", description = "设置与对象关联的扩展数据 (XData)")
    public void setXData(@Parameter(description = "扩展数据类型数组") short[] dataType, @Parameter(description = "扩展数据值数组") Variant[] dataValue) {
        SafeArray typeArray = new SafeArray(Variant.VariantShort, dataType.length);
        for (int i = 0; i < dataType.length; i++) {
            typeArray.setShort(i, dataType[i]);
        }
        SafeArray valueArray = new SafeArray(Variant.VariantVariant, dataValue.length);
        for (int i = 0; i < dataValue.length; i++) {
            valueArray.setVariant(i, dataValue[i]);
        }
        Disp.call(getClass(), dispatch, "SetXData", new Variant(typeArray), new Variant(valueArray));
    }

    @Override
    public String toString() {
        return Internal.format("{}(Type: {}, ObjectID: {}, Handle: {})",
                this.getClass().getSimpleName(),
                getObjectName(), getObjectId(), getHandle());
    }

    @Override
    public int hashCode() {
        return getHandle().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AcadObject iObject) {
            return getHandle().equals(iObject.getHandle());
        }
        return false;
    }

    /**
     * 扩展数据 (XData) 结果封装。
     *
     * @param dataType 数据类型数组（short 型整数代码）
     * @param dataValue 数据值数组
     */
    public record XData(short[] dataType, Variant[] dataValue) {
    }

}