package com.syh.chapterseven;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Stream流的中间方法 测试
 */
public class IntermediateTest {
    IntStream intStream = null;

    @Before
    public void init () {
        //1、调用IntStream的builder()方法获取IntStream.Builder对象
        IntStream.Builder builder = IntStream.builder();
        //2、往Builder里增加元素
        builder.add(1)
                .add(5)
                .add(2)
                .add(2)
                .add(3)
                .add(3)
                .add(4);
        //3、调用Builder的build()方法，得到IntStream对象
        intStream = builder.build();
    }

    /**
     * filter(Predicate predicate)方法
     */
    @Test
    public void predicate () {
        intStream = this.intStream.filter(ele -> ele == 2);
        intStream.forEach(obj -> System.out.println("obj = " + obj));
    }

    /**
     * mapToXxx(ToXxxFunction mapper)
     */
    @Test
    public void mapToInt () {
        DoubleStream doubleStream = intStream.mapToDouble(ele -> Double.parseDouble(String.valueOf(ele)));
        doubleStream = doubleStream.map(ele -> ele / 3);
        doubleStream.forEach(ele -> System.out.println("ele = " + ele));

    }

    /**
     * peek(Consumer action)，对元素的改变不会影响到原集合
     */
    @Test
    public void peek () {
        IntStream peek = intStream.peek(ele -> System.out.println("eleSS = " + ++ele));
        peek.forEach(ele -> System.out.println("ele = " + ele));
    }

    /**
     * distinct()
     */
    @Test
    public void distinct () {
        IntStream distinct = intStream.distinct();
        distinct.forEach(obj -> System.out.println("obj = " + obj));
    }

    /**
     * sorted()
     */
    @Test
    public void sorted () {
        IntStream sorted = intStream.sorted();
        sorted.forEach(obj -> System.out.println("obj = " + obj));
    }

    /**
     * limit(long maxSize)
     */
    @Test
    public void limit () {
        IntStream limit = intStream.limit(3L);
        limit.forEach(obj -> System.out.println("obj = " + obj));
    }
}
