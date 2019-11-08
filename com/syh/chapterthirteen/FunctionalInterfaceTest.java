package com.syh.chapterthirteen;

/**
 * @FunctionalInterface定义函数式接口——验证
 */
@FunctionalInterface
public interface FunctionalInterfaceTest {
    //定义一个抽象方法
    void test();
    //定义第二个抽象方法就会报错
    //void set();

    //定义一个默认方法
    default void hehe () {
        System.out.println("hehe()方法");
    }

    //定义一个static方法
    static void gogo () {
        System.out.println("gogo()方法");
    }
}
