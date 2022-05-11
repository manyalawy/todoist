package com.todoist.list.service;

import com.todoist.list.model.Task;
import com.todoist.list.model.TodoList;
import com.todoist.list.repo.TodoListRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Service
public class TodolistService {
    private final TodoListRepo todoListRepo;

    public Map createList(TodoList todoList) {
        todoListRepo.save(todoList);
        Map result = new HashMap<String, Object>();
        result.put("success", true);
        result.put("data", todoList);
        return result;
    }

    public Map addCollaborator(String user_id, String list_id){
        Optional<TodoList> todoList = todoListRepo.findById(list_id);
        if(todoList.isPresent()){
            TodoList list = todoList.get();
            if(list.getCollaborators() == null){
                list.setCollaborators(new ArrayList<>());
            }
            list.getCollaborators().add(user_id);
            todoListRepo.save(list);
        }
        Map result = new HashMap<String, Object>();
        result.put("success", true);
        result.put("data", todoList);
        return result;
    }




    public List<TodoList> searchlistbyname(String name) {

        return todoListRepo.findByName(name);

    }




}
