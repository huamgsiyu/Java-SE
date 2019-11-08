package com.syh.chaptersix;

/**
 * Runtime类
 */
public class RuntimeTest {
    public static void main(String[] args) {
        /**
         * 获取Runtime实例
         */
        Runtime runtime = Runtime.getRuntime();
        System.out.println("runtime = " + runtime);
        /**
         * 获取 处理器数量
         */
        int i = runtime.availableProcessors();
        System.out.println("i = " + i);

        /**
         * 获取空闲内存书
         */
        System.out.println("空闲内存量 = " + runtime.freeMemory());

        /**
         * 获取总内存数
         */
        System.out.println("总内存数：" + runtime.totalMemory());

        /**
         * 可用最大内存数
         */
        System.out.println("可用最大内存数：" + runtime.maxMemory());
    }
}
