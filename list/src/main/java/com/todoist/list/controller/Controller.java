package com.todoist.list.controller;

import com.mongodb.client.result.InsertOneResult;
import com.todoist.list.commands.CreateTodolist;
import com.todoist.list.config.Helpers;
import com.todoist.list.config.RMQConfig.CreateTodolistRMQ;
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

    @RabbitListener(queues = CreateTodolistRMQ.RPC_MESSAGE_QUEUE)
    public void process(Message message) {
        byte[] body = message.getBody();
        Helpers helpers = new Helpers();
        JSONObject jsonObject = helpers.parseToJson(new String(body));
        CreateTodolist createTodolist = new CreateTodolist((String) jsonObject.get("name"), (String) jsonObject.get("user_id"));
        InsertOneResult result = createTodolist.execute();
        System.out.println("Inserted a document with the following id: "
                + result.getInsertedId().asObjectId().getValue());
        //This is the message to be returned by the server
        Message build = MessageBuilder.withBody(("Inserted a document with the following id: "
                + result.getInsertedId().asObjectId().getValue()).getBytes()).build();
        CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
        rabbitTemplate.sendAndReceive(CreateTodolistRMQ.RPC_EXCHANGE, CreateTodolistRMQ.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
    }

}
