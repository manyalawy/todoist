package com.todoist.list.service;

import com.todoist.list.model.TodoList;
import com.todoist.list.repo.TodoListRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TodolistService {
    private final TodoListRepo todoListRepo;

    public String createList(TodoList todoList) {
        todoListRepo.save(todoList);
        return "done";
    }
}
