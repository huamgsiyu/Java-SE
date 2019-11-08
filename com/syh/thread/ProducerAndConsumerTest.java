package com.syh.thread;

/**
 * 生产者消费者案例
 */
public class ProducerAndConsumerTest {
    /**
     * 伪代码
     * 1、类——
     *  生产者类
     *      属性——产品
     *      行为——调用产品的生产行为
     *  消费者类
     *      属性——产品
     *      行为——调用产品的消费行为
     *  产品类
     *      属性——容量
     *      行为——生产、消费
     */
    public static void main(String[] args) {
        Product product = new Product(2);
        Producer producer = null;
        Consumer consumer = null;
        for (int i = 0; i < 100; i++) {
            producer = new Producer(product);
            producer.start();
            consumer = new Consumer(product);
            consumer.start();
        }
    }
}
