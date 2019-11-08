package com.syh.chapterseven;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * TreeSet比HashSet额外增加的方法
 */
public class TreeSetTest {
    TreeSet<Integer> treeSet = new TreeSet<>();
    
    @Before
    public void init () {
        treeSet.add(1);
        treeSet.add(23);
        treeSet.add(3);
        treeSet.add(5);
        treeSet.add(-1);
    }

    /**
     * 1 Comparator comparator()
     */
    @Test
    public void comparator () {
        Comparator<? super Integer> comparator = treeSet.comparator();
        System.out.println("comparator = " + comparator);
    }
    /**
     * 2 Object first()
     */
    @Test
    public void first () {
        Integer first = treeSet.first();
        System.out.println("first = " + first);
    }
    /**
     * 3 Object last()
     */
    @Test
    public void last () {
        Integer last = treeSet.last();
        System.out.println("last = " + last);
    }
    /**
     * 4 Object lower(Object e)
     */
    @Test
    public void lower () {
        Integer lower = treeSet.lower(2);
        System.out.println("lower = " + lower);
    }
    /**
     * 5 Object higher(Object e)
     */
    @Test
    public void higher () {
        Integer higher = treeSet.higher(5);
        System.out.println("higher = " + higher);
    }
    /**
     * 6 SortedSet subSet(Object fromElement, Object toElement)
     */
    @Test
    public void subSet () {
        SortedSet<Integer> integers = treeSet.subSet(-1, 5);
        Stream<Integer> stream = integers.stream();
        stream.forEach(obj -> System.out.println("obj = " + obj));
    }
    /**
     * 7 Sorted headSet(Object toElement)
     */
    @Test
    public void headSet () {
        SortedSet<Integer> integers = treeSet.headSet(5);
        Stream<Integer> stream = integers.stream();
        stream.forEach(obj -> System.out.println("obj = " + obj));
    }
    /**
     * 8 SortedSet tailSet(Object fromElement)
     */
    @Test
    public void tailSet () {
        SortedSet<Integer> integers = treeSet.tailSet(5);
        Stream<Integer> stream = integers.stream();
        stream.forEach(obj -> System.out.println("obj = " + obj));
    }
}
