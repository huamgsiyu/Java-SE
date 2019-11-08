package com.syh.thread;

/**
 * 多线程问题：
 */
public class Test {
    public static void main(String[] args) {
        Account account = new Account("hsy", 1000.0);

        DrawThread drawThread1 = new DrawThread("A", account, 600);
        DrawThread drawThread2 = new DrawThread("B", account, 600);

        drawThread1.start();
        drawThread2.start();
    }
}
