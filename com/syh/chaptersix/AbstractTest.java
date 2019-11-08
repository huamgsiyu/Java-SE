package com.syh.chaptersix;

/**
 * 被定义为抽象类的三种情况
 *  1、有抽象方法
 */
public class AbstractTest {
    public static void main(String[] args) {
        //抽象类不能够被new实例化，不能调用构造器，构造器主要提供给子类调用
//        Car car = new Car();

    }
}

abstract class Car extends CarAbstract{
    @Override
    public void run() {

    }
}

abstract class CarAbstract {
    //1、有抽象方法
    abstract void run ();


    abstract void go ();
}
    //2、继承抽象类，但没有完全实现抽象类的抽象方法
abstract class CarA extends CarAbstract{
    abstract void run ();
}

interface CarInterface {
    abstract void run1Interface();


    abstract void run2Interface();
}
    //3、实现了包含抽象方法的接口，但没有完全实现该接口的所有方法时
abstract class CarIntefaceTest implements CarInterface {

    @Override
    public void run1Interface() {

    }
}

