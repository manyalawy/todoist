package com.todoist.list.controller;

import com.todoist.list.model.TodoList;
import com.todoist.list.service.TodolistService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "api/todolist")
@AllArgsConstructor
public class TodoListController {
    private final TodolistService todolistService;

    @PostMapping(path = "/create")
    public Map createList(@RequestBody TodoList todoList){
        Map result = todolistService.createList(todoList);
        return result;
    }
}
