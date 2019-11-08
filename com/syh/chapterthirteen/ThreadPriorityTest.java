package com.syh.chapterthirteen;

public class ThreadPriorityTest {
    public static void main(String[] args) {
        int mainPriority = Thread.currentThread().getPriority();
        System.out.println("mainPriority = " + mainPriority);
        Thread.currentThread().setPriority(6);

        int alterMainPriority = Thread.currentThread().getPriority();
        System.out.println("alterMainPriority = " + alterMainPriority);
    }
}
