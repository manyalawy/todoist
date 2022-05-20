package com.todoist.board;

import com.todoist.board.config.Consumer;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class board {


    public static void main(String[] args) throws IOException, TimeoutException {
        Consumer consumer = new Consumer();
        consumer.consume();
    }

}
