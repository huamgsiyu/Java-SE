package com.syh.thread;

/**
 * 消费者线程
 */
public class Consumer extends Thread{

    private Product product;

    public Consumer() {

    }
    public Consumer (Product product) {
        this.product = product;
    }

    @Override
    public void run () {
        product.consumer();
    }
}
