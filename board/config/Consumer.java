package com.todoist.board.config;

import com.rabbitmq.client.*;
import com.todoist.board.commands.CreateTaskInBoard;
import com.todoist.board.commands.CreateSubtaskInBoard;
import com.todoist.board.commands.CreateTodolist;
import com.todoist.board.commands.DeleteTaskFromBoard;

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
            CreateTaskInBoard createTask = new CreateTaskInBoard((String) jsonObject.get("name"), (String) jsonObject.get("todolist_id"));
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
            CreateSubtaskInBoard createSubtask = new CreateSubtaskInBoard((String) jsonObject.get("name"), (String) jsonObject.get("task_id"));
            createSubtask.execute();
        }, consumerTag -> {

        });
        channel.basicConsume("delete-task-from-board", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            Helpers helpers = new Helpers();
            JSONObject jsonObject = helpers.parseToJson(s);
            DeleteTaskFromBoard createSubtask = new DeleteTaskFromBoard((String) jsonObject.get("name"), (String) jsonObject.get("task_id"));
            createSubtask.execute();
        }, consumerTag -> {

        });

    }


}
