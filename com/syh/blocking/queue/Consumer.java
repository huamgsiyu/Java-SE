package com.syh.blocking.queue;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread{
    private BlockingQueue<Integer> queue;

    public Consumer() {
    }

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run () {
        try {
            sleep(1000);
            Integer take = queue.take();
            System.out.println(getName() + "拿出成功，值 = " + take);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
