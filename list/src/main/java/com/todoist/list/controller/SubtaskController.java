package com.todoist.list.controller;

import com.todoist.list.service.SubtaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/todolist")
@AllArgsConstructor
public class SubtaskController {
    private final SubtaskService subtaskService;
}
