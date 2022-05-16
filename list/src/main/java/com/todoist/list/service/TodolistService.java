//package com.todoist.list.service;
//
//import com.todoist.list.model.Task;
//import com.todoist.list.model.TodoList;
//import com.todoist.list.repo.TodoListRepo;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//@AllArgsConstructor
//@Service
//public class TodolistService {
//    private final TodoListRepo todoListRepo;
//
//    public Map createList(TodoList todoList) {
//        todoListRepo.save(todoList);
//        Map result = new HashMap<String, Object>();
//        result.put("success", true);
//        result.put("data", todoList);
//        return result;
//    }
//
//
//
//
//    public List<TodoList> searchlistbyname(String name) {
//
//        return todoListRepo.findByName(name);
//
//    }
//
//
//
//
//}
