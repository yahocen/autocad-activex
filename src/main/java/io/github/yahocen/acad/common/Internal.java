// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.common;

import java.lang.reflect.Constructor;

/**
 * 库内部小工具，替代浅层第三方依赖（hutool 等），保持依赖面最小。
 * 不是对外 API，消费方不应依赖此类。
 */
public final class Internal {

    private Internal() {}

    /**
     * {} 占位符格式化（StrUtil.format 的等价物，顺序替换，null 参数输出 "null"）。
     */
    public static String format(String template, Object... args) {
        if (template == null) return null;
        if (args == null || args.length == 0) return template;
        StringBuilder sb = new StringBuilder(template.length() + 32);
        int argIndex = 0;
        int i = 0;
        while (i < template.length()) {
            char c = template.charAt(i);
            if (c == '{' && i + 1 < template.length() && template.charAt(i + 1) == '}'
                    && argIndex < args.length) {
                sb.append(args[argIndex++]);
                i += 2;
            } else {
                sb.append(c);
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * 用单参构造器创建实例（ReflectUtil.newInstance 的等价物，按参数类型匹配）。
     * 实体类的构造器均接收 Dispatch。
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> clazz, Object arg) {
        for (Constructor<?> ctor : clazz.getDeclaredConstructors()) {
            if (ctor.getParameterCount() == 1 && ctor.getParameterTypes()[0].isInstance(arg)) {
                try {
                    ctor.setAccessible(true);
                    return (T) ctor.newInstance(arg);
                } catch (ReflectiveOperationException e) {
                    throw new IllegalStateException("无法实例化 " + clazz.getName(), e);
                }
            }
        }
        throw new IllegalStateException(clazz.getName() + " 没有匹配 " + arg.getClass().getName() + " 的单参构造器");
    }

    /**
     * 休眠，被中断时恢复中断标志（比 hutool ThreadUtil.sleep 吞中断更规范）。
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** 数组判空（null 或长度 0）。 */
    public static boolean isEmpty(Object[] arr) {
        return arr == null || arr.length == 0;
    }
}
