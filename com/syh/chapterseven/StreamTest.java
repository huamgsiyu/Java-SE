package com.syh.chapterseven;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

/**
 * Stream的使用步骤
 */
public class StreamTest {
    public static void main(String[] args) {

        /*
            1、使用Stream或XXXStream的builder()类方法
                创建该Stream对应的Builder
        */
        IntStream.Builder builder = IntStream.builder();
        /*
            2、重复调用Builder的add()方法向该流中添加元素
         */
        builder.add(1)
                .add(2)
                .add(3)
                .add(4);
        /*
            3、调用Builder的build()方法获取对应的Stream
         */
        IntStream build = builder.build();

        /*
            5、加入中间方法，把流中的所有元素值变成原来的2倍再加1
         */
        build = build.map(element -> element * 2 + 1);

        /*
            4、调用XXXStream的聚集方法
         */
        double average = build.average().getAsDouble();
        System.out.println("average = " + average);

        /**
         * 如果再调用聚集方法，就会出现异常
         * Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
         */
//        int min = build.min().getAsInt();
//        System.out.println("min = " + min);


    }
}
