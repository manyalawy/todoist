package com.todoist.server.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoist.server.config.Board.*;

@RestController
@RequestMapping("/api/todoist")
public class BoardController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("board/create")
    public Map CreateBoard(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("create-board");
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

    @PostMapping("board/section/create")
    public Map CreateSection(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("create-section");
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
    @PostMapping("board/section/task/create")
    public Map CreateTaskInSection(@RequestBody String body) throws ParseException, IOException, TimeoutException {
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

    @PostMapping("board/section/task/subtask/create")
    public Map CreateSubtaskInSection(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("create-subtask");
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

    @PostMapping("board/section/task/assign")
    public Map AssignTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("assign-task");
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

    @PostMapping("board/section/task/edit")
    public Map EditTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("edit-task");
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

    @PostMapping("board/section/edit")
    public Map EditSection(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("edit-section");
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

    @GetMapping("board/search")
    public Map BoardSearch(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("search-board");
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

    @GetMapping("board/section/task/search")
    public Map TaskSearch(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("search-task");
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
    @PostMapping("board/section/task/comment/add")
    public Map AddCommentTaskSection(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("add-comment-section");
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

    @PostMapping("board/comment/add")
    public Map AddCommentBoard(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("add-comment-board");
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


    @PostMapping("board/collaborator/add")
    public Map AddCollaborator(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("add-collaborator");
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

    @DeleteMapping("board/section/delete")
    public Map DeleteSection(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("delete-section");
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

    @DeleteMapping("board/section/task/delete")
    public Map DeleteTaskFromSection(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("delete-task-sectio");
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

    @DeleteMapping("board/task/delete")
    public Map DeleteTaskFromBoard(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("delete-task-board");
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

    @GetMapping("board/section/task/deadline")
    public Map DeadlineTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("task-deadline");
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

    @GetMapping("board/section/sort")
    public Map SortSections(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("sort-section");
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

    @GetMapping("board/section/task/sort")
    public Map SortTasks(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        // Create a message subject
        HashMap res = new HashMap<>();
        Message newMessage = MessageBuilder.withBody(body.getBytes()).build();
        newMessage.getMessageProperties().setMessageId("sort-task");
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
}