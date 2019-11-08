package com.syh.chaptereight;

/**
 * 	当一个异常出现时，单靠某个方法无法完全处理该异常，必须由几个方法协作才可完全处理该异常。
 * 	也就是说，在异常出现的当前方法中，程序只对异常进行部分处理，还有些处理需要在该方法的调用
 * 	者中才能完成，所以应该再次抛出异常，让该方法的调用者也能捕获到异常。
 */
public class AuctionTest {
    public static void main(String[] args) {
        set();
    }

    public static void set()  {
        try {
            action();
        } catch (Exception e) {
            System.out.println("错误处理2");
        }
    }

    public static void action () throws Exception {
        try {
            int[] array = null;
            array.toString();
        } catch (Exception e) {
            System.out.println("错误处理1");
            throw new Exception("str");
        }
    }
}
