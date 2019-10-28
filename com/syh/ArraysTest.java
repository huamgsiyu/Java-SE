package com.syh;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author HSY
 * @version 1.1
 *
 * 数组工具类Arrays的使用
 */
public class ArraysTest {
    int[] array = {-1, -2, 10, 4, 25, 6};


    /**
     * 数组转为List
     */
    @Test
    public void test(){
        List<int[]> arrayList = Arrays.asList(array);
        for (int[] array : arrayList) {
            for (int obj : array) {
                System.out.println("obj = " + obj);
            }
        }
    }

    /**
     * 看懂Arrays的binarySearch方法，理解并实现二进制查找算法。
     */
    @Test
    public void test1(){
        System.out.println("array.length = " + array.length);
        int i = binarySearch(array, 0, array.length, 0);
        System.out.println("i = " + i);


    }

    /**
     * 二分查找算法
     */
    public static int binarySearch(int[] a, int formIndex, int endIndex, int key){
        int low = formIndex;
        int high = endIndex - 1;
        int mid = (low + high) >>> 1;
        int midValue = a[mid];

        while (low <= high){
            if (midValue < key)
                low = mid + 1;
            else if(midValue > key)
                high = mid - 1;
            else
                return mid; //key found
        }
        return -(mid + 1);  //key not found
    }

    /**.
     * 测试Arrays的sort方法，研究里面用到那个排序算法
     *  1、使用的是DualPivotQuicksort这个新快排类
     *      源码：当排序个数小于QUICKSORT_THRESHOLD时，采用快速排序算法
     *      // private static final int QUICKSORT_THRESHOLD = 286;
     *      // Use Quicksort on small arrays
     *         if (right - left < QUICKSORT_THRESHOLD) {
     *             sort(a, left, right, true);
     *             return;
     *         }
     */
    @Test
    public void test3(){
//        Arrays.sort(array);
        insertSort(array, 3, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println("array = " + array[i]);
        }
    }

    private static void insertSort(int[] a, int left, int right){
        for (int i = left, j = i; i < right; j = ++i) {
            int ai = a[i + 1];
            while (ai < a[j]) {
                a[j + 1] = a[j];
                if (j-- == left) {
                    break;
                }
            }
            a[j + 1] = ai;
        }
    }

    //快速排序
    public void quick_sort (int s[], int l, int r) {
        if (l < r) {
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l];
            while (i < j) {
                while(i < j && s[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if(i < j)
                    s[i++] = s[j];

                while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if(i < j)
                    s[j--] = s[i];
            }
            s[i] = x;
            quick_sort(s, l, i - 1); // 递归调用
            quick_sort(s, i + 1, r);
        }
    }

    @Test
    public void test4(){
        bubbleSort(array, 0, array.length - 1);
//        bubbleSortDown(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println("array[i] = " + array[i]);
        }
    }
    /**
     * 冒泡排序算法——升序
     */
    public static void bubbleSort(int[] a, int left, int right){
        //控制比较轮数
        for (int i = left; i < right; i++) {
            //控制每轮比较次数
            for(int j = 0; j < right - i; j++){
                int temp = a[j + 1];
                if(temp < a[j]){
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
    /**
     * 冒泡排序算法——降序
     */
    public static void bubbleSortDown(int[] a, int left, int right){

        for (int i = left; i < right; i ++) {
            for (int j = 0; j < right - i; j ++){
                int temp = a[j + 1];
                if (temp > a[j]) {
                   a[j + 1] = a[j];
                   a[j] = temp;
                }
            }
        }
    }

    /**
     * 测试归并排序
     */
    @Test
    public void test6() {
        mergeSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.println("array = " + array[i]);
        }
    }

    /**
     * 归并排序
     */
    private void mergeSort(int[] a, int p, int r) {
        //循环终止条件：p > r，即当每一部分只有一个element时
        if (p < r) {
            int q = (p + r) >>> 1;
            mergeSort(a, p, q);
            mergeSort(a, q + 1, r);
            merge(a, p, q, r);
        }
    }
    private static void merge (int[] a, int p, int q, int r) {
        //左边部分个数
        int left = q - p + 1;
        //右边部分个数
        int right = r - q;
        //左右两部数组
        int[] lefts = new int[left];
        int[] rights = new int[right];

        int i = 0;
        int j = 0;
        int k = 0;
        //给两边数组赋值，这两个数组组合起来就是要排序的数组
        for (i = 0, k = p; i < left; i++, k++) {
            lefts[i] = a[k];
        }
        for (j = 0, k = q + 1; j < right; j++, k++){
            rights[j] = a[k];
        }
        //对着两个数组里面比较并合并
        for (i = 0, j = 0, k = p; i < left && j < right; k++) {
            if (lefts[i] > rights[j]) {
                a[k] = rights[j++];
            }else {
                a[k] = lefts[i++];
            }
        }
        //如果左边还有剩余，放入数组中
        while (i < left) {
            a[k++] = lefts[i++];
        }
        //如果右边还有剩余，放入数组中
        while (j < right) {
            a[k++] = rights[j++];
        }
    }
}
