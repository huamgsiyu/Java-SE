package com.syh.reflect;

import java.lang.reflect.Array;

/**
 * Array类的使用
 */
public class ArrayTest {
    public static void main(String[] args) {
        //创建一个元素类型为String，长度为10的数组
        Object arr = Array.newInstance(String.class, 10);
        //依次为arr数组中index为5/6的元素赋值
        Array.set(arr, 5, "哈哈");
        Array.set(arr, 6, "嘿嘿");

        Object haha = Array.get(arr, 5);
        System.out.println("haha = " + haha);
        Object heihei = Array.get(arr, 6);
        System.out.println("heihei = " + heihei);

    }
}
