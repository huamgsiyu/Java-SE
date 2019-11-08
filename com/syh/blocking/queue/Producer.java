package com.syh.blocking.queue;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread{
    private BlockingQueue<Integer> queue;

    public Producer() {
    }

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    //生产产品
    @Override
    public void run () {
        try {
            sleep(100);
            queue.put(1);
            System.out.println(getName() + "放入成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
