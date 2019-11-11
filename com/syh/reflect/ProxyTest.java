package com.syh.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 */
public class ProxyTest {
    public static void main(String[] args) {
        //创建一个InvocationHandler对象
        MyInvocationHandler handler = new MyInvocationHandler();
        Persons persons = (Persons) Proxy.newProxyInstance(Persons.class.getClassLoader(),
                new Class[]{Persons.class},
                handler);
        persons.walk();
        persons.sayHello("您好！");
    }
}

class MyInvocationHandler implements InvocationHandler {
    /*
        执行动态代理对象的所有方法时，都会被替换成执行如下的invoke方法
            其中：
                proxy->代表动态代理对象
                method->代表正在执行的方法
                args->代表调用目标方法时传入的实参
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---正在执行的方法：" + method);
        if (args != null) {
            System.out.println("下面是执行该方法时传入的实参为：");
            for (Object val : args) {
                System.out.println("实参：" + val);
            }
        } else {
            System.out.println("调用该方法没有实参！");
        }
        return null;
    }
}



interface Persons {
    void walk ();
    void sayHello (String name);
}
