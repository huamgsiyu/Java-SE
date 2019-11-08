package com.syh.chaptereight;

import java.sql.SQLException;

/**
 * 	把底层的原始异常直接传给用户是一种不负责任的表现。通常的做法是：
 * 		（1）程序先捕获原始异常，然后抛出一个新的业务异常
 * 		（2）新的业务异常中包含了对用户的提示信息
 * 	以上的处理方式被称为异常转译。
 */
public class SalExceptionTest {

    public static void main(String[] args) {

    }

    public void calSal () {
        try {

        } catch (NullPointerException e) {

        } catch (Exception e) {

        }
    }
}
