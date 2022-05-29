package com.todoist.server.controller;

import com.todoist.server.config.List.*;
import com.todoist.server.config.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/todolist")
public class ListController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/create")
    public Map createTodolist(@RequestBody String body){
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("create-todolist");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("task/create")
    public Map createTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("create-task");
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }

    @PostMapping("subtask/create")
    public Map createSubtask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        //The customer sends a message
        Message result = rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_MESSAGE_QUEUE, newMessage);
        String response = "";
        if (result != null) {
            // To get message sent correlationId
            String correlationId = newMessage.getMessageProperties().getCorrelationId();
            // Get response header information
            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();
            // Access server Message returned id
            String msgId = (String) headers.get("spring_returned_message_correlation");
            if (msgId.equals(correlationId)) {
                response = new String(result.getBody());
            }
        }
        res.put("msg", response);
        return res;
    }
    @PostMapping("task/assign")
    public Map assignTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        Producer producer =  new Producer();
        producer.produceMessage("assign-task", body);
        HashMap res = new HashMap<>();
        res.put("success", true);
        return res;
    }

    @PostMapping("task/edit")
    public Map editTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        Producer producer =  new Producer();
        producer.produceMessage("edit-task", body);
        HashMap res = new HashMap<>();
        res.put("success", true);
        return res;
    }

    @GetMapping("/search")
    public Map searchList(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        Producer producer =  new Producer();
       
        producer.produceMessage("search-list", body);


//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(RabbitConfiguration.class);
//        AmqpTemplate template = context.getBean(AmqpTemplate.class);
//


//        ListSearch ListSearch =new ListSearch((String) jsonObject.get("name"));
//
//        ListSearch.execute();


//        template.convertAndSend("myqueue", "Hello from RabbitMQ!  test ");




        HashMap res = new HashMap<>();
        res.put("success", true);
        return res;
    }

    @GetMapping("task/search")
    public Map searchTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        Producer producer =  new Producer();

        producer.produceMessage("search-task", body);



        HashMap res = new HashMap<>();
        res.put("success", true);
        return res;
    }

    @GetMapping("task/sort")
    public Map sortTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        Producer producer =  new Producer();

        producer.produceMessage("sort-task", body);



        HashMap res = new HashMap<>();
        res.put("success", true);
        return res;
    }

    @GetMapping("task/deadline")
    public Map deadlineTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        Producer producer =  new Producer();

        producer.produceMessage("deadline-task", body);



        HashMap res = new HashMap<>();
        res.put("success", true);
        return res;
    }

    @DeleteMapping("task/delete")
    public Map deleteTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        Producer producer =  new Producer();
        producer.produceMessage("delete-task", body);
        HashMap res = new HashMap<>();
        res.put("success", true);
        return res;
    }

    @PostMapping("comments/add")
    public Map addComment(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        Producer producer =  new Producer();
        producer.produceMessage("add-comment", body);
        HashMap res = new HashMap<>();
        res.put("success", true);
        return res;
    }

    @PostMapping("collaborators/add")
    public Map addCollaborator(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        Producer producer =  new Producer();
        producer.produceMessage("add-collaborator", body);
        HashMap res = new HashMap<>();
        res.put("success", true);
        return res;
    }

    @PostMapping("test")
    public void test(@RequestBody String body){
        ApplicationContext context =
                new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        AmqpTemplate template = context.getBean(AmqpTemplate.class);

       template.convertAndSend("myqueue", "TESTTTTTT finall ");



    }
}
