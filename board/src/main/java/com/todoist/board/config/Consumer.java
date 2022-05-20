package com.todoist.list.config;

import com.rabbitmq.client.*;
import com.todoist.list.commands.CreateSubtask;
import com.todoist.list.commands.CreateTask;
import com.todoist.list.commands.CreateTodolist;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        channel.queueDeclare("create-subtask",false, false, false, null);
        channel.basicConsume("create-task", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            Helpers helpers = new Helpers();
            JSONObject jsonObject = helpers.parseToJson(s);
            CreateTask createTask = new CreateTask((String) jsonObject.get("name"), (String) jsonObject.get("todolist_id"));
            createTask.execute();
        }, consumerTag -> {

        });
        channel.basicConsume("create-todolist", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            Helpers helpers = new Helpers();
            JSONObject jsonObject = helpers.parseToJson(s);
            CreateTodolist createTodolist = new CreateTodolist((String) jsonObject.get("name"), (String) jsonObject.get("user_id"));
            createTodolist.execute();
         }, consumerTag -> {

        });
        channel.basicConsume("create-subtask", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            Helpers helpers = new Helpers();
            JSONObject jsonObject = helpers.parseToJson(s);
            CreateSubtask createSubtask = new CreateSubtask((String) jsonObject.get("name"), (String) jsonObject.get("task_id"));
            createSubtask.execute();
        }, consumerTag -> {

        });

    }


}
