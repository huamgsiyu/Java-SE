package com.syh.chaptereight;

public class NullPointExceptionTest {
    public static void main(String[] args) {

        try {
            String str = null;
            str.length();
        } catch (ClassCastException | NullPointerException e) {
            System.out.println("e.getMessage() = " + e.getMessage());

            e.printStackTrace();

            System.out.println("e.getStackTrace() = " + e.getStackTrace());
        }
    }
}
