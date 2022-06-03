package com.boards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

// import com.boards.config.Consumer;
@SpringBootApplication
@RestController
@RequestMapping("/home")
public class BoardApplication {


    public static void main(String[] args) throws IOException, TimeoutException {
        		SpringApplication.run(BoardApplication.class, args);

    }

}
