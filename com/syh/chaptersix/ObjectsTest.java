package com.syh.chaptersix;

import java.util.Objects;

public class ObjectsTest {
    public static void main(String[] args) {
        OutClass outClass = new OutClass();
        String s = Objects.toString(outClass);
        System.out.println("s = " + s);
        outClass = null;
        Objects.requireNonNull(outClass);

    }
}
