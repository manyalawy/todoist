package com.todoist.server.controller;

import com.todoist.server.config.Producer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api/todolist")
public class ListController {

    @PostMapping("/create")
    public Map createTodolist(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        Producer producer =  new Producer();
        producer.produceMessage("create-todolist", body);
        HashMap res = new HashMap<>();
        res.put("success", true);
        return res;
    }

    @PostMapping("task/create")
    public Map createTask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        Producer producer =  new Producer();
        producer.produceMessage("create-task", body);
        HashMap res = new HashMap<>();
        res.put("success", true);
        return res;
    }

    @PostMapping("subtask/create")
    public Map createSubtask(@RequestBody String body) throws ParseException, IOException, TimeoutException {
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);
        Producer producer =  new Producer();
        producer.produceMessage("create-subtask", body);
        HashMap res = new HashMap<>();
        res.put("success", true);
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

        HashMap res = new HashMap<>();
        res.put("success", true);
        return res;
    }








}
