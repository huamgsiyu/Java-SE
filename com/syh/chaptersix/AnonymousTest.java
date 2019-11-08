package com.syh.chaptersix;

/**
 * 2、定义一个接口，并使用匿名内部类方式创建接口的实例
 */
public class AnonymousTest {
    public static void main(String[] args) {
        AnonymousTest anonymousTest = new AnonymousTest();

        Anonymous anonymous = new Anonymous() {
            @Override
            public void say () {

            }
        };
        anonymous.say();

        FunctionTest functionTest = () -> {
            System.out.println("使用Lambda表达式创建函数式接口");
        };
    }
}

interface Anonymous {
    void say ();
}

/**
 * 3、定义一个函数式接口，并使用Lambda表达式创建函数式接口的实例
 */

@FunctionalInterface
interface FunctionTest {
    void test ();
}

/**
 * 4、
 */
