package com.syh.chapterthreehomework;

/**
 * 多态的验证
 */
public class PolymorphismTest {
    public static void main(String[] args) {
        //编译时类型为Fruit，运行时类型为Apple
        Fruit fruitApple = new Apple();
        //编译时类型/运行时类型都是Pear
        Fruit fruitPear = new Pear();
        //编译时类型/运行时类型都是Fruit
        Fruit fruit = new Fruit();

        //调用Fruit中的say()方法，实际上是调用其子类Apple的say方法
        fruitApple.say();
        //调用Fruit中的say()方法，实际上是调用其子类Pear的say方法
        fruitPear.say();
        //调用Fruit中的say()方法，就是调用的Fruit类的say方法
        fruit.say();

        /**
         * 结论，对于同一个类型Fruit而言，同样是调用say()方法，但是表现出不同的行为特征，这就是多态
         */

    }
}

class Fruit {
    public void say () {
        System.out.println("我是水果");
    }
}

class Apple extends Fruit {
    @Override
    public void say (){
        System.out.println("我是苹果");
    }
}

class Pear extends Fruit {
    @Override
    public void say () {
        System.out.println("我是梨子");
    }
}
