package com.todoist.list.controller;

import com.mongodb.client.result.InsertOneResult;
import com.todoist.list.commands.*;
import com.todoist.list.config.*;
import com.todoist.list.config.RMQConfig.*;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Controller {

    @Autowired
    RabbitTemplate rabbitTemplate;



    @RabbitListener(queues = RabbitMQConfig.RPC_MESSAGE_QUEUE)
    public void listener(Message message) {
        byte[] body = message.getBody();
        Helpers helpers = new Helpers();
        JSONObject jsonObject = helpers.parseToJson(new String(body));
        switch (message.getMessageProperties().getMessageId()){
            case "create-todolist": {
                CreateTodolist createTodolist = new CreateTodolist((String) jsonObject.get("name"), (String) jsonObject.get("user_id"));
                InsertOneResult result = createTodolist.execute();
                System.out.println("Inserted a document with the following id: "
                        + result.getInsertedId().asObjectId().getValue());
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Inserted a document with the following id: "
                        + result.getInsertedId().asObjectId().getValue()).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            case "create-task": {
                CreateTask createTask = new CreateTask((String) jsonObject.get("name"), (String) jsonObject.get("todolist_id"));
                InsertOneResult result = createTask.execute();
                System.out.println("Inserted a document with the following id: "
                        + result.getInsertedId().asObjectId().getValue());
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Inserted a document with the following id: "
                        + result.getInsertedId().asObjectId().getValue()).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            case "create-subtask": {
                CreateSubtask createSubtask = new CreateSubtask((String) jsonObject.get("name"), (String) jsonObject.get("task_id"));
                InsertOneResult result = createSubtask.execute();
                System.out.println("Inserted a document with the following id: "
                + result.getInsertedId().asObjectId().getValue());
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Inserted a document with the following id: "
                + result.getInsertedId().asObjectId().getValue()).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
            }
        }
    }

}
