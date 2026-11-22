package io.github.yahocen.acad.common;

import io.github.yahocen.jacob.com.Dispatch;
import io.github.yahocen.jacob.com.Variant;
import io.swagger.v3.oas.annotations.media.Schema;
import io.github.yahocen.acad.document.base.AcadObject;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * AutoCAD ActiveX Collection对象封装
 * <a href="https://help.autodesk.com/view/OARX/2024/CHS/?guid=GUID-589F246A-41BE-4D38-AA91-9FDA3ABABEA6">About Collection Objects (ActiveX)</a>
 * <p>
 * AutoCAD 中所有 Collection 对象（Layers/Documents/SelectionSets/ModelSpace 等）
 * 在 COM 类型库中都继承自 IAcadObject，因此本类继承 {@link AcadObject}。
 * </p>
 * 
 * @author Yahocen
 * @since 2.0
 */
@Schema(
    title = "ComCollection 集合基类",
    description = "AutoCAD ActiveX Collection对象的封装基类"
)
public abstract class ComCollection<T> extends AcadObject implements Iterable<T> {

    public ComCollection(Dispatch collection) {
        super(collection);
    }

    /**
     * 获取集合中的元素数量
     * 
     * @return 集合元素数量
     */
    public int count() {
        return Disp.get(getClass(), dispatch,"Count").getInt();
    }

    /**
     * Groovy 属性访问兼容：让 {@code ms().count} 也能工作（等同于 {@code ms().count()}）。
     * Groovy 的 {@code obj.count} 会查找 {@code getCount()} 方法，找不到才报 property 错误。
     */
    public int getCount() {
        return count();
    }

    /**
     * 通过索引获取集合元素
     * 
     * @param index 从0开始的索引
     * @return 集合元素对象
     */
    public T item(int index) {
        return wrap(Disp.call(getClass(), dispatch,"Item", new Variant(index)).getDispatch());
    }

    /**
     * 通过名称获取集合元素
     * 
     * @param name 元素名称
     * @return 集合元素对象
     */
    public T item(String name) {
        return wrap(Disp.call(getClass(), dispatch,"Item", new Variant(name)).getDispatch());
    }

    /**
     * 将 Dispatch 包装为具体的 Java 类型
     * 
     * @param dispatch 底层 Dispatch 对象
     * @return 包装后的 Java 类型
     */
    protected abstract T wrap(Dispatch dispatch);

    /**
     * 判断集合是否为空
     * 
     * @return true 如果集合为空
     */
    public boolean isEmpty() {
        return count() == 0;
    }

    public boolean isNotEmpty() {
        return !isEmpty();
    }

    /**
     * 支持 for-each 遍历：{@code for (def e : ms()) { ... }}
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private final int total = count();
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < total;
            }

            @Override
            public T next() {
                if (cursor >= total) {
                    throw new NoSuchElementException();
                }
                return item(cursor++);
            }
        };
    }

}
