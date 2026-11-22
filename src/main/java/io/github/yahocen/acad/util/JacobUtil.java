// Copyright 2026 Yahocen
// SPDX-License-Identifier: Apache-2.0
package io.github.yahocen.acad.util;

import io.github.yahocen.jacob.com.SafeArray;
import io.github.yahocen.jacob.com.Variant;
import io.github.yahocen.acad.document.base.Point;

/**
 * @author Yahocen
 */
public class JacobUtil {

    /**
     * 根据 Java 类型获取变量值
     * @param val
     * @param clazz
     * @return
     */
    public static Object getVariantValue(Variant val, Class<?> clazz) {
        if(clazz.equals(String.class)) {
            return val.getString();
        }
        if(clazz.equals(int.class) || clazz.equals(Integer.class)) {
            return val.getInt();
        }
        if(clazz.equals(double.class) || clazz.equals(Double.class)) {
            return val.getDouble();
        }
        if(clazz.equals(boolean.class) || clazz.equals(Boolean.class)) {
            return val.getBoolean();
        }
        if(clazz.equals(float.class) || clazz.equals(Float.class)) {
            return val.getFloat();
        }
        if(clazz.equals(long.class) || clazz.equals(Long.class)) {
            return val.getLong();
        }
        if(clazz.equals(short.class) || clazz.equals(Short.class)) {
            return val.getShort();
        }
        if(clazz.equals(char.class) || clazz.equals(Character.class)) {
            return (char) val.getShort();
        }
        if(clazz.equals(byte.class) || clazz.equals(Byte.class)) {
            return (byte) val.getShort();
        }
        if(clazz.equals(SafeArray.class)) {
            return val.toSafeArray();
        }
        return val;
    }

    /**
     * 将 Java 对象转为 Variant
     * @param obj 待转换对象
     * @return Variant
     */
    public static Variant toVariant(Object obj) {
        if(obj instanceof Variant variant) {
            return variant;
        }
        if(obj instanceof Point point) {
            return point.toVariant();
        }
        if(obj instanceof Integer integer) {
            return new Variant(integer.intValue());
        }
        if(obj instanceof Double aDouble) {
            return new Variant(aDouble.doubleValue());
        }
        if(obj instanceof Boolean bool) {
            return new Variant(bool.booleanValue());
        }
        if(obj instanceof String string) {
            return new Variant(string);
        }
        if(obj instanceof Float floatVal) {
            return new Variant(floatVal.floatValue());
        }
        if(obj instanceof Long longVal) {
            return new Variant(longVal.longValue());
        }
        if(obj instanceof Short shortVal) {
            return new Variant(shortVal.shortValue());
        }
        if(obj instanceof Character charVal) {
            return new Variant((short)charVal.charValue());
        }
        if(obj instanceof Byte byteVal) {
            return new Variant((short) byteVal);
        }
        if(obj instanceof SafeArray safeArray) {
            return new Variant(safeArray);
        }
        if(obj instanceof int[] intArray) {
            SafeArray sa = new SafeArray(Variant.VariantInt, intArray.length);
            for(int i = 0; i < intArray.length; i++) {
                sa.setInt(i, intArray[i]);
            }
            return new Variant(sa);
        }
        if(obj instanceof double[] doubleArray) {
            SafeArray sa = new SafeArray(Variant.VariantDouble, doubleArray.length);
            for(int i = 0; i < doubleArray.length; i++) {
                sa.setDouble(i, doubleArray[i]);
            }
            return new Variant(sa);
        }
        if(obj instanceof String[] stringArray) {
            SafeArray sa = new SafeArray(Variant.VariantString, stringArray.length);
            for(int i = 0; i < stringArray.length; i++) {
                sa.setString(i, stringArray[i]);
            }
            return new Variant(sa);
        }
        if(obj instanceof boolean[] boolArray) {
            SafeArray sa = new SafeArray(Variant.VariantBoolean, boolArray.length);
            for(int i = 0; i < boolArray.length; i++) {
                sa.setBoolean(i, boolArray[i]);
            }
            return new Variant(sa);
        }
        return new Variant(obj);
    }

}