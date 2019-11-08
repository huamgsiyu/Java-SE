package com.syh.chapterseven;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Iterator类的常用方法
 */
public class IteratorTest {
    Collection<String> collection = new ArrayList<>();
    Iterator<String> iterator = null;
    /**
     * boolean hasNext()
     * Object next()
     */
    @Before
    public void init () {
        collection.add("1");
        collection.add("2");
        collection.add("3");
        collection.add("4");
        iterator = collection.iterator();
    }
    @Test
    public void hasNext () {
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println("next = " + next);
        }
    }

    /**
     * void remove()
     */
    @Test
    public void remove () {
        while (iterator.hasNext()) {
            String next = iterator.next();
            iterator.remove();
            break;
        }
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println("next = " + next);
        }
    }

    /**
     * void forEachRemaining(Consumer action)
     */
    @Test
    public void forEachRemaining () {
        iterator.forEachRemaining(obj -> System.out.println("obj = " + obj));
    }
}
