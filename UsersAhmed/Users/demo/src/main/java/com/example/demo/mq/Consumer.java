package com.example.demo.mq;

import com.example.demo.user.AddUserCommand;
import com.example.demo.user.deleteUserCommand;
import com.example.demo.user.getUsersCommand;
import com.example.demo.user.updateUserCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public Consumer() {
    }

    public void consume() throws IOException, TimeoutException {
        ConnectionFactory factory =  new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("addUser",false, false, false, null);
        channel.queueDeclare("deleteUser",false, false, false, null);
        channel.queueDeclare("getUsers",false, false, false, null);
        channel.queueDeclare("updateUser",false, false, false, null);

        channel.basicConsume("addUser", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            HashMap<String,Object> result = new ObjectMapper().readValue(s, HashMap.class);
            AddUserCommand addUserCommand = new AddUserCommand();
            addUserCommand.execute(result);
        }, consumerTag -> {

        });

        channel.basicConsume("deleteUser", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            HashMap<String,Object> result = new ObjectMapper().readValue(s, HashMap.class);
            deleteUserCommand deleteUserCommand = new deleteUserCommand();
            deleteUserCommand.execute(result);
        }, consumerTag -> {

        });

        channel.basicConsume("getUsers", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            HashMap<String,Object> result = new ObjectMapper().readValue(s, HashMap.class);
            getUsersCommand getUsersCommand = new getUsersCommand();
            getUsersCommand.execute(result);
        }, consumerTag -> {

        });

        channel.basicConsume("updateUser", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            HashMap<String,Object> result = new ObjectMapper().readValue(s, HashMap.class);
            updateUserCommand updateUserCommand = new updateUserCommand();
            updateUserCommand.execute(result);
        }, consumerTag -> {

        });

    }


}
