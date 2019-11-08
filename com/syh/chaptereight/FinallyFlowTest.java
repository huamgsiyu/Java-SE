package com.syh.chaptereight;

/**
 *  一般情况下，不要在finally块中使用return或throw等导致方法终止的语句，
 *  一旦在finally块中使用了return或throw语句，
 *  将会导致try块、catch中的return、throw语句失效
 */
public class FinallyFlowTest {
    public static void main(String[] args) {

        System.out.println(" test(); = " +  test());
    }

    public static int test () {
        try {
            String str = null;
            str.length();
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }
}

