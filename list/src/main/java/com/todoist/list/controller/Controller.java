package com.todoist.list.controller;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
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

import java.util.Date;

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
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Inserted a document with the following id: "
                + result.getInsertedId().asObjectId().getValue()).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            case "assign-task": {
                AssignToTask assignToTask = new AssignToTask((String) jsonObject.get("task_id"), (String) jsonObject.get("user_id"));
                String result = assignToTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            case "edit-task": {
                EditTask editTask= new EditTask((String) jsonObject.get("task_id"), (String) jsonObject.get("task_name"),(String) jsonObject.get("piority"),(Date) jsonObject.get("due_date"),(Boolean) jsonObject.get("done"));
                String result = editTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            case "search-todolist": {
                ListSearch searchList= new ListSearch( (String) jsonObject.get("list_name"));
                String result = searchList.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            case "search-task": {
                TaskSearch searchTask= new TaskSearch( (String) jsonObject.get("task_name"));
                String result = searchTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            case "sort-task": {
                SortTask sortTask = new SortTask( (String) jsonObject.get("sort_by"),(String) jsonObject.get("order"));
                String result = sortTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            case "task-deadline": {
                TaskDeadline deadlineTask = new TaskDeadline( );
                String result = deadlineTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }


            case "delete-task": {
                DeleteTask deleteTask = new DeleteTask((String) jsonObject.get("task_id"), (String) jsonObject.get("todolist_id"));
                DeleteResult result = deleteTask.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody(("Task was deleted successfully").getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }

            case "add-collaborator": {
                AddCollaborator addCollaborator = new AddCollaborator((String) jsonObject.get("task_id"), (String) jsonObject.get("todolist_id"));
                String result = addCollaborator.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            case "add-comment": {
                AddComment addComment = new AddComment((String) jsonObject.get("comment"), (String) jsonObject.get("task_id"));
                String result = addComment.execute();
                //This is the message to be returned by the server
                Message build = MessageBuilder.withBody((result).getBytes()).build();
                CorrelationData correlationData = new CorrelationData(message.getMessageProperties().getCorrelationId());
                rabbitTemplate.sendAndReceive(RabbitMQConfig.RPC_EXCHANGE, RabbitMQConfig.RPC_REPLY_MESSAGE_QUEUE, build, correlationData);
                break;
            }
            default:
                break;
        }
    }

}
