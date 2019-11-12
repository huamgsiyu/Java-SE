package com.syh.reflect;

import java.lang.reflect.InvocationTargetException;

/**
 * 普通对象工厂——需要强制类型转换
 * 泛型对象工厂——不需要强制类型转换
 */
public class ObjectTest {
    public static void main(String[] args) {
        ArrayTest arrayTest = (ArrayTest)ObjectFactory.getInstance("com.syh.reflect.ArrayTest");
        System.out.println("arrayTest1 = " + arrayTest);

        ArrayTest instance = GennericsObjectFacotry.getInstance(ArrayTest.class);
        System.out.println("arrayTest2 = " + instance);
    }
}

/**
 * 普通对象工厂
 */
class ObjectFactory {
    public static Object getInstance (String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}

/**
 * 泛型对象工厂
 */
class GennericsObjectFacotry {
    public static <T> T getInstance (Class<T> t) {
        try {
            return t.getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
