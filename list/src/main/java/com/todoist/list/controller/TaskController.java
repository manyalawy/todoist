package com.todoist.list.controller;

import com.todoist.list.model.Task;
import com.todoist.list.service.TaskService;
import com.todoist.list.service.TodolistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "api/todolist")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("task/create/{list_id}")
    public Map createTask(@RequestBody Task task, @PathVariable("list_id") String list_id){
        Map result = taskService.createTask(task, list_id);
        return result ;
    }
}
