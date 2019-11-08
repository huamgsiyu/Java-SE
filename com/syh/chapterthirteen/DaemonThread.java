package com.syh.chapterthirteen;

public class DaemonThread {

    public static void main(String[] args) {
        DaemonRunnable daemonRunnable = new DaemonRunnable();
        Thread thread = new Thread(daemonRunnable);
        thread.setDaemon(true);
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}

class DaemonRunnable implements Runnable {
    @Override
    public void run () {
        //由于main线程提前结束，导致DaemonRunnable线程也只能退出
        for (int i = 0; i < 100000; i++) {
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}
