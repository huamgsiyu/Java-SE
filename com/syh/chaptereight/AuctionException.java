package com.syh.chaptereight;

/**
 * 自定义异常
 */
public class AuctionException extends Exception{

    public AuctionException () {}
    public AuctionException (String msg) {
        super(msg);
    }
}
