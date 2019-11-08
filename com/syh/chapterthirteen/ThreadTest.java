package com.syh.chapterthirteen;

/**
 * 继承Thread来创建线程
 */
public class ThreadTest extends Thread {
    public static void main(String[] args) {
        /**
         * 1、定义Thread类的子类，并重写run()方法
         * 2、创建该Thread子类的实例
         * 3、调用对象的start()方法启动线程
         */
        MyThread aThread = new MyThread();
        aThread.setName("A");
        MyThread bThread = new MyThread();
        bThread.setName("B");
        aThread.start();
        bThread.start();
    }
}
class MyThread extends Thread {
    @Override
    public void run () {
        System.out.println("MyThread线程类:" + Thread.currentThread().getName());
    }
}