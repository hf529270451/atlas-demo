package com.hfyd.atlas.framework.natlv.utils;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TUtils {
    public static Object callMethod(Object paramObject, String paramString, Class[] paramArrayOfClass, Object[] paramArrayOfObject)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method localMethod = paramObject.getClass().getDeclaredMethod(paramString, paramArrayOfClass);
        localMethod.setAccessible(true);
        return localMethod.invoke(paramObject, paramArrayOfObject);
    }

    public static Class getActualClass(Class paramClass) {
        Type[] actualTypeArguments = ((ParameterizedType) paramClass.getGenericSuperclass()).getActualTypeArguments();
        Type type = actualTypeArguments[0];
        Log.e("Class", "getActualClass: " + actualTypeArguments );
        return (Class) type;
    }

    public static Object getValue(Object paramObject, String paramString)
            throws IllegalAccessException, NoSuchFieldException {
        Field localField = paramObject.getClass().getDeclaredField(paramString);
        localField.setAccessible(true);
        return localField.get(paramObject);
    }

    public static void setValue(Object paramObject1, String paramString, Object paramObject2)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field localField = paramObject1.getClass().getDeclaredField(paramString);
        localField.setAccessible(true);
        localField.set(paramObject1, paramObject2);
    }

    public static String toLowerCaseFirstOne(String paramString) {
        if (Character.isLowerCase(paramString.charAt(0)))
            return paramString;
        return Character.toLowerCase(paramString.charAt(0)) + paramString.substring(1);
    }

    public static String toUpperCaseFirstOne(String paramString) {
        if (Character.isUpperCase(paramString.charAt(0)))
            return paramString;
        return Character.toUpperCase(paramString.charAt(0)) + paramString.substring(1);
    }
}