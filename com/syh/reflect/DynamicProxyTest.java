package com.syh.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * AOP中的动态代理案例
 *  1个接口
 *  1个接口实现
 *  1个拦截类
 *  1个InvocationHandler实现
 *  1个代理工厂
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        Dog target = new GunDog();
        Dog dog = (Dog)MyProxyFactory.getProxy(target);
        dog.info();
        dog.run();
    }
}
interface Dog {
    void info ();
    void run ();
}
class GunDog implements Dog {

    @Override
    public void info() {
        System.out.println("我是一只猎狗");
    }

    @Override
    public void run() {
        System.out.println("我跑得贼快");
    }
}
class DogUtil {
    //第一个拦截方法
    public void method1 () {
        System.out.println("模拟第一个通用方法");
    }
    //第二个拦截方法
    public void method2 () {
        System.out.println("模拟第二个通用方法");
    }
}
class MineInvocationHandler implements InvocationHandler {
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        DogUtil dogUtil = new DogUtil();
        dogUtil.method1();
        Object result = method.invoke(target, args);
        dogUtil.method2();
        return result;
    }
}

class MyProxyFactory {
    public static Object getProxy (Object target) {
        MineInvocationHandler handler = new MineInvocationHandler();
        handler.setTarget(target);
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);
    }
}