package com.boards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// import com.boards.config.Consumer;
@SpringBootApplication
@ComponentScan({"com.boards.controller"})
public class BoardApplication {


    public static void main(String[] args) throws IOException, TimeoutException {
        		SpringApplication.run(BoardApplication.class, args);

    }

}
