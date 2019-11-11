package com.syh.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

class Test {
    public void replace (String str, List<String> list) {}
}

/**
 * 使用反射获取Test类对象的replace方法的形参信息
 */
public class MethodParameterTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<Test> clazz = Test.class;
        Method replaceMethod = clazz.getMethod("replace", String.class, List.class);
        String methodName = replaceMethod.getName();
        System.out.println("methodName = " + methodName);

        System.out.println("获取replace方法的形参信息");
        int parameterCount = replaceMethod.getParameterCount();
        System.out.println("形参个数 = " + parameterCount);
        Parameter[] parameters = replaceMethod.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println("参数类型 = " + parameter.getType());
            System.out.println("参数名称 = " + parameter.getName());
            System.out.println("泛型类型 = " + parameter.getParameterizedType());
        }
    }
}
