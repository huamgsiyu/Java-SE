package com.syh.chapterthirteen;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<>((Callable<Integer>) () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("ThreadName = " + Thread.currentThread().getName() + ", i = " + i);
            }
            return 1;
        });

        Thread thread = new Thread(task);
        thread.start();
    }
}

