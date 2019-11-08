package com.syh.blocking.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Test {
    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);

        Producer producer = null;
        Consumer consumer = null;
        for (int i = 0; i < 10; i++) {
            producer = new Producer(queue);
            consumer = new Consumer(queue);
            producer.start();
            consumer.start();
        }
    }
}
