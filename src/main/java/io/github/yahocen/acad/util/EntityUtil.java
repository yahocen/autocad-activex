package io.github.yahocen.acad.util;

import cn.hutool.core.util.ReflectUtil;
import io.github.yahocen.jacob.com.Dispatch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import io.github.yahocen.acad.Application;
import io.github.yahocen.acad.common.AcadEntityName;
import io.github.yahocen.acad.document.base.AcadEntity;
import io.github.yahocen.acad.document.element.ExternalReference;

import io.github.yahocen.jacob.com.SafeArray;
import io.github.yahocen.jacob.com.Variant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Yahocen
 */
@SuppressWarnings("unchecked")
@Slf4j
public class EntityUtil {

    /**
     * 实体映射表：由 codegen 生成的 {@link EntityRegistry} 提供，类加载即就绪。
     * 未收录的 ObjectName，parse(Dispatch) 会 fallback 到基类 AcadEntity。
     */
    private static final Map<String, Class<? extends AcadEntity>> ENTITIES = EntityRegistry.ENTITIES;

    @Operation(summary = "解析实体", description = "将 Dispatch 对象解析为指定类型的实体")
    public static <T extends AcadEntity> T parse(@Parameter(description = "Dispatch 对象") Dispatch acadElement, @Parameter(description = "实体类型") Class<T> clazz) {
        return ReflectUtil.newInstance(clazz, acadElement);
    }

    @Operation(summary = "解析实体", description = "根据对象名称自动解析为对应类型的实体")
    public static AcadEntity parse(@Parameter(description = "Dispatch 对象") Dispatch acadElement) {
        String objectName = Dispatch.get(acadElement, "ObjectName").getString();
        // 外部参照与普通块参照的 ObjectName 都是 AcDbBlockReference（AutoCAD 2024 实测），只能看所属块的 IsXRef
        if (BLOCK_REFERENCE.equals(objectName) && isXRef(acadElement)) {
            return new ExternalReference(acadElement);
        }
        Class<? extends AcadEntity> objectClazz = ENTITIES.get(objectName);
        if(null == objectClazz) {
            log.error("No class found for objectName: {}", objectName);
            return new AcadEntity(acadElement);
        }
        return parse(acadElement, objectClazz);
    }

    /**
     * 把 COM 返回的实体数组（GetAttributes / Explode / GetLoopAt 等的 VT_ARRAY|VT_DISPATCH 或 VT_ARRAY|VT_VARIANT）
     * 解析为实体列表。非数组（Empty / Nothing）返回空列表。
     */
    @Operation(summary = "解析实体数组", description = "将 COM 返回的实体数组解析为实体列表，非数组返回空列表")
    public static List<AcadEntity> parseArray(@Parameter(description = "实体数组 Variant") Variant array) {
        List<AcadEntity> out = new ArrayList<>();
        if (array == null || (array.getvt() & Variant.VariantArray) == 0) {
            return out;
        }
        SafeArray sa = array.toSafeArray();
        try {
            for (Variant v : sa.toVariantArray()) {
                if (v.getvt() == Variant.VariantDispatch) {
                    out.add(parse(v.toDispatch()));
                }
            }
        } finally {
            sa.safeRelease();
        }
        return out;
    }

    private static final String BLOCK_REFERENCE = "AcDbBlockReference";

    /** 块参照所引用的块是否为外部参照；读取失败时按普通块参照处理。 */
    private static boolean isXRef(Dispatch blockReference) {
        try {
            var name = Dispatch.get(blockReference, "Name");
            var document = Dispatch.get(blockReference, "Document").toDispatch();
            var blocks = Dispatch.get(document, "Blocks").toDispatch();
            var block = Dispatch.call(blocks, "Item", name).toDispatch();
            return Dispatch.get(block, "IsXRef").getBoolean();
        } catch (Exception e) {
            log.debug("判断块参照是否为外部参照失败，按普通块参照处理", e);
            return false;
        }
    }

}
