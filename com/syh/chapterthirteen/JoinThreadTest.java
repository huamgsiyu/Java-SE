package com.syh.chapterthirteen;

/**
 * 控制线程——join()方法
 */
public class JoinThreadTest {
    public static void main(String[] args) throws InterruptedException {

        JoinThread join = null;
        for (int i = 0; i < 10; i++) {
            join = new JoinThread();
            join.setName("join->" + i);
            join.start();
            join.join();
        }
    }
}
class JoinThread extends Thread {
    @Override
    public void run () {
        System.out.println(Thread.currentThread().getName());
    }
}
