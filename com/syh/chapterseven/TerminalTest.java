package com.syh.chapterseven;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Stream类的常用末端方法
 */
public class TerminalTest {
    List<String> list = null;

    @Before
    public void init () {
        list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
    }

    /**
     * 1、forEach(Consumer action)
     */
    @Test
    public void forEach () {
        Stream<String> stream = list.stream();
        stream.forEach(obj -> System.out.println("obj = " + obj));
    }
    /**
     * 2、toArray()
     */
    @Test
    public void toArray () {
        Stream<String> stream = list.stream();
        Object[] objects = stream.toArray();
        for (Object obj : objects) {
            System.out.println("obj = " + obj);
        }
    }
    /**
     * 3、reduce(BinaryOperator<T> accumulator)
     *      apply方法体内的先比较，得到最后一个值
     */
    @Test
    public void reduce () {
        Stream<String> stream = list.stream();
        Stream<String> stream1 = stream.reduce(new BinaryOperator<String>() {
            @Override
            public String apply(String s, String s2) {
                if (s.length() > s2.length()) {
                    return s;
                } else {
                    return s2;
                }
            }
        }).stream();
        stream1.forEach(obj -> System.out.println("obj = " + obj));
    }

    /**
     * 3、reduce(T identity, BinaryOperator<T> accumulator)
     *      apply方法体内的先比较，得到最后一个值，然后按照方法体里的
     *      规则继续与提供的初始值进行比较
     */
    @Test
    public void reduce2 () {
        Stream<String> stream = list.stream();
        String reduce = stream.reduce("111", new BinaryOperator<String>() {
            @Override
            public String apply(String s, String s2) {
                if (s.length() > s2.length()) {
                    return s;
                }
                return s2;
            }
        });
        System.out.println("reduce = " + reduce);
    }

    /**
     * 3、reduce(U identity,
     *                  BiFunction<U, ? super T, U> accumulator,
     *                  BinaryOperator<U> combiner)
     *  （1）identity: 一个初始化的值；这个初始化的值其类型是泛型U，与Reduce方法返回的类型一致；
     * 注意此时Stream中元素的类型是T，与U可以不一样也可以一样，这样的话操作空间就大了；
     * 不管Stream中存储的元素是什么类型，U都可以是任何类型，如U可以是一些基本数据类型的
     * 包装类型Integer、Long等；或者是String，又或者是一些集合类型ArrayList等；后面会说到这些用法。
     *
     *  （2）accumulator: 其类型是BiFunction，输入是U与T两个类型的数据，而返回的是U类型；也就是
     *  说返回的类型与输入的第一个参数类型是一样的，而输入的第二个参数类型与Stream中元素类型是一样的。
     *
     *  （3）combiner: 其类型是BinaryOperator，支持的是对U类型的对象进行操作；
     *
     *  说明：3个参数，第一个是初始值，第2和第3个不能同时执行，第2个是非并行环境下执行；
     *  第3个是并行环境下执行。
     */
    @Test
    public void reduce3 () {
        Stream<String> stream = list.stream();
        String reduce = stream.reduce("222",
                new BiFunction<String, String, String>() {
                    //非并行环境下执行
                    @Override
                    public String apply(String s, String s2) {
                        System.out.println("bibi");
                        if (s.length() > s2.length()) {
                            return s;
                        }
                        return s2;
                    }
                },
                new BinaryOperator<String>() {
                    //并行环境下执行
                    @Override
                    public String apply(String s, String s2) {
                        System.out.println("aiai");
                        if (s.length() > s2.length()) {
                            return s;
                        }
                        return s2;
                    }
                });
        System.out.println("reduce = " + reduce);
    }

    /**
     * 4 min()
     */
    @Test
    public void min () {
        OptionalInt min = list.stream().mapToInt((Integer::parseInt)).min();
        System.out.println("min = " + min);
    }

    /**
     * 5 max()
     */
    @Test
    public void max () {
        OptionalInt max = list.stream().mapToInt((Integer::parseInt)).max();
        System.out.println("max = " + max);
    }
    /**
     * 6 count()
     */
    @Test
    public void count () {
        long count = list.stream().count();
        System.out.println("count = " + count);
    }
    /**
     * 7 anyMatch(Predicate predicate)
     */
    @Test
    public void anyMatch () {
        boolean h = list.stream().anyMatch(ele -> ("12345").indexOf(ele) != -1);
        System.out.println("h = " + h);
    }

    /**
     * 8 allMatch(Predicate predicate)
     */
    @Test
    public void allMatch () {
        boolean b = list.stream().allMatch(ele -> ele.length() == 1);
        System.out.println("b = " + b);
    }
    /**
     * 9 noneMatch(Predicate predicate)
     */
    @Test
    public void noneMatch () {
        boolean ss = list.stream().noneMatch(ele -> ele.contains("SS"));
        System.out.println("ss = " + ss);
    }

    /**
     * 10 findFirst()
     */
    @Test
    public void findFirst () {
        Optional<String> first = list.stream().findFirst();
        System.out.println("first = " + first);
    }
    /**
     * 11 findAny()
     */
    @Test
    public void findAny () {
        Optional<String> any = list.stream().findAny();
        System.out.println("any = " + any);
    }
}
