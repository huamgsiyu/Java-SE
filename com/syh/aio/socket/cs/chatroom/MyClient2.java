package com.syh.aio.socket.cs.chatroom;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MyClient2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        MyClient myClient = new MyClient();
        myClient.startClient();
        myClient.connect();
    }
}
