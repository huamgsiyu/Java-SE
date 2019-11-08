package com.syh.chaptersix;

/**
 * 1、通过抽象类定义车类的模板，然后通过抽象的车类
 * 来派生拖拉机、卡车、小轿车
 */
public abstract class CarMode {
    abstract void run ();
}

/**
 * 拖拉机车
 */
class Tractors extends CarMode{

    @Override
    void run() {
        System.out.println("拖拉机开车了");
    }
}

/**
 * 卡车
 */
class Truck extends CarMode{

    @Override
    void run() {
        System.out.println("卡车开车了");
    }
}

/**
 * 小轿车
 */
class Sedan extends CarMode{

    @Override
    void run() {
        System.out.println("小轿车开车了");
    }
}
