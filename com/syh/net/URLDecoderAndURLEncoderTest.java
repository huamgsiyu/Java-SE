package com.syh.net;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URLDecoder和URLEncoder测试
 */
public class URLDecoderAndURLEncoderTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String enc = "utf-8";
        String keyword = "我是大帅哥！";
        String encode = URLEncoder.encode(keyword, enc);
        System.out.println("encode = " + encode);   //%E6%88%91%E6%98%AF%E5%A4%A7%E5%B8%85%E5%93%A5%EF%BC%81

        String decode = URLDecoder.decode(encode, enc);
        System.out.println("decode = " + decode);   //我是大帅哥！
    }
}
