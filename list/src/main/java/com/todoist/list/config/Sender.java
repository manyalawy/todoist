package com.todoist.list.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Sender {

    public static void main(String[] args) throws IOException, TimeoutException {
       ConnectionFactory factory =  new ConnectionFactory();
       factory.setUsername("myuser");
       factory.setPassword("mypassword");
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare("create-task",false, false, false, null);
            String message = "is this an F";
            channel.basicPublish("", "create-task", false, null, message.getBytes());
            System.out.println("Message sent");

        }

    }
}
