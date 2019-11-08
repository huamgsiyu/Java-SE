package com.syh.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 产品类
 */
public class Product {
    //最大容量
    private int capacity;
    //当前产品数
    private int production;

    private ReentrantLock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    public Product() {
    }

    public Product(int capacity) {
        this.capacity = capacity;
    }

    //生产产品
    public void production () {
        lock.lock();
        try {
            if (production < capacity) {
                Thread.sleep(0);
                production++;
                System.out.println(Thread.currentThread().getName() + "成功生产，当前产品库存为：" + production);
            } else {
                System.out.println(Thread.currentThread().getName() + "当前库存已满，无法继续生产——等待消费");
                condition.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //消费产品
    public void consumer () {
        lock.lock();
        try {
            if (production > 0) {
                Thread.sleep(0);
                production--;
                System.out.println(Thread.currentThread().getName() + "成功消费，当前产品库存为：" + production);
            } else {
                System.out.println(Thread.currentThread().getName() + "当前库存为空，无法取出商品进行消费——等待生产产品");
                condition.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
