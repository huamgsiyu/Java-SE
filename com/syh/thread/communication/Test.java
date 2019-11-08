package com.syh.thread.communication;


/**
 * 多线程问题：
 */
public class Test {
    public static void main(String[] args) {
        Account account = new Account("hsy", 1000.0);

        DrawThread drawThread = null;
        DepositThread depositThread = null;

        for (int i = 0; i < 10; i++) {
            drawThread = new DrawThread("A", account, 1000);;
            depositThread = new DepositThread(account, 1000.0);
            drawThread.start();
            depositThread.start();

        }
    }
}
