package com.example.demo.constants;


import com.example.demo.mq.Consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class User {

    public static void main(String[] args) throws IOException, TimeoutException {
        Consumer consumer = new Consumer();
        consumer.consume();
    }

}
