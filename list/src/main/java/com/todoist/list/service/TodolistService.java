package com.todoist.list.service;

import com.todoist.list.repo.TodoListRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TodolistService {
    private final TodoListRepo todoListRepo;
}
