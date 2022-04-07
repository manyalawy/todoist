package com.todoist.list.controller;

import com.todoist.list.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/todolist")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
}
