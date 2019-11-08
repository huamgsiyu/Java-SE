package com.syh.thread.group;

public class ThreadGroupTest {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("myThreadGroup");
        Thread thread = null;
        MyThread myThread = null;
        for (int i = 0; i < 10; i++) {
            myThread = new MyThread();
            thread = new Thread(threadGroup, myThread);
            thread.start();
        }
        System.out.println("threadGroup.getName() = " + threadGroup.getName());
        System.out.println("threadGroup.activeCount() = " + threadGroup.activeCount());
        System.out.println("threadGroup.getParent().getName() = " + threadGroup.getParent().getName());
    }
}

class MyThread implements Runnable {
    @Override
    public void run () {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getThreadGroup());
    }
}
