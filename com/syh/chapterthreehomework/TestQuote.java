package com.syh.chapterthreehomework;

import org.junit.Test;

/**
 * 参数传递
 */
public class TestQuote {
    /**
     * 基本类型的参数传递——仅仅是值传递，改变值并不会修改实参的值
     * @param args
     */
    public static void main (String[] args) {
        int a = 6;
        int b = 9;
        swap(a, b);
        System.out.println("main->a = " +a + ", b = " + b );
    }
    public static void swap (int a, int b) {
        a = 9;
        b = 6;
        System.out.println("swap->a = " +a + ", b = " + b );
    }

    /**
     * 引用类型的参数传递——也是值传递，但是为什么会改变数组会被改变了呢？
     *  因为当传入a数组时，在change()方法栈中复制了a数组的引用，引用指向堆内存中的a数组。既然quoteTest()和change()中都有引用指向a数组，
     *  那么它们都可以通过引用改变了堆内存中数组的值，让人产生了传递的是实参的假象。
     */
    @Test
    public void quoteTest(){
        int[] a = {0, 1, 2, 3};
        change(a);
        System.out.println("quoteTest->");
        for (int obj : a) {
            System.out.println("obj = " + obj);
        }
    }
    public void change(int[] a){
        a[0] = 100;
        System.out.println("change");
        for (int obj : a) {
            System.out.println("obj = " + obj);
        }
    }
}
