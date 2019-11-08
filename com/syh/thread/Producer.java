package com.syh.thread;

/**
 * 生产者线程
 */
public class Producer extends Thread{
    private Product product;

    public Producer() {
    }

    public Producer(Product product) {
        this.product = product;
    }

    @Override
    public void run () {
//        singleThread();
//        multithreading();
        product.production();
    }

    /**
     * 单线程环境
     */
    public void singleThread () {
        product.production();
    }

    /**
     * 多线程环境——同步代码块
     */
    public void multithreading () {
        synchronized (product) {
            product.production();
        }
    }
}
