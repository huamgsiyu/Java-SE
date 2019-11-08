package com.syh.chapterthirteen;

/**
 * 通过实现Runnable接口来创建线程
 */
public class RunnableTest {
    public static void main(String[] args) {
        /**
         * 1、MyRunnable实现Runnable接口
         * 2、创建MyRunnable对象
         * 3、把MyRunnable对象装入Thread中
         * 4、调用start()方法
         */
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run () {
        System.out.println("MyRunnable");
    }
}
