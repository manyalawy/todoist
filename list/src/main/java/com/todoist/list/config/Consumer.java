package com.todoist.list.config;

import com.rabbitmq.client.*;
import com.todoist.list.commands.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Date;
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
        channel.queueDeclare("assign-task",false, false, false, null);
        channel.queueDeclare("edit-task",false, false, false, null);
        channel.queueDeclare("search-list",false, false, false, null);



        channel.queueDeclare("delete-task",false, false, false, null);
        channel.queueDeclare("add-comment",false, false, false, null);
        channel.queueDeclare("add-collaborator",false, false, false, null);
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

        channel.basicConsume("assign-task", false, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            Helpers helpers = new Helpers();
            JSONObject jsonObject = helpers.parseToJson(s);
            AssignToTask AssignToTask =new AssignToTask((String) jsonObject.get("task_id"),(String) jsonObject.get("assignee"));

            AssignToTask.execute();
        }, consumerTag -> {

        });

        channel.basicConsume("edit-task", false, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            Helpers helpers = new Helpers();
            JSONObject jsonObject = helpers.parseToJson(s);
            EditTask EditTask =new EditTask((String) jsonObject.get("task_id"),(String) jsonObject.get("name"), (String) jsonObject.get("priority"),(Date) jsonObject.get("due_date"),(Boolean) jsonObject.get("done"));

            EditTask.execute();
        }, consumerTag -> {

        });

        channel.basicConsume("search-list", false, (consumerTag, message) -> {

            String s = new String(message.getBody(), "UTF-8");


            Helpers helpers = new Helpers();
            JSONObject jsonObject = helpers.parseToJson(s);
            ListSearch ListSearch =new ListSearch((String) jsonObject.get("name"));

            ListSearch.execute();
        }, consumerTag -> {

        });






        channel.basicConsume("delete-task", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            Helpers helpers = new Helpers();
            JSONObject jsonObject = helpers.parseToJson(s);
            DeleteTask deleteTask = new DeleteTask((String) jsonObject.get("task_id"), (String) jsonObject.get("list_id"));
            deleteTask.execute();
        }, consumerTag -> {

        });
        channel.basicConsume("add-comment", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            Helpers helpers = new Helpers();
            JSONObject jsonObject = helpers.parseToJson(s);
            AddComment addComment = new AddComment((String) jsonObject.get("content"), (String) jsonObject.get("task_id"));
            addComment.execute();
        }, consumerTag -> {

        });
        channel.basicConsume("add-collaborator", true, (consumerTag, message) -> {
            String s = new String(message.getBody(), "UTF-8");
            Helpers helpers = new Helpers();
            JSONObject jsonObject = helpers.parseToJson(s);
            AddCollaborator addCollaborator = new AddCollaborator((String) jsonObject.get("user_id"), (String) jsonObject.get("list_id"));
            addCollaborator.execute();
        }, consumerTag -> {

        });



    }


}
