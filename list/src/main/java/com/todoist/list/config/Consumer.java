package com.todoist.list.config;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public Consumer() {
    }

    public void consume() throws IOException, TimeoutException {
        ConnectionFactory factory =  new ConnectionFactory();
        factory.setUsername("myuser");
        factory.setPassword("mypassword");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("create-task",false, false, false, null);
        channel.queueDeclare("create-todolist",false, false, false, null);
        channel.basicConsume("create-task", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            System.out.println("task created");
        }, consumerTag -> {

        });
        channel.basicConsume("create-todolist", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            System.out.println("todolist created");
        }, consumerTag -> {

        });
    }


}
