package com.todoist.list.controller;

import com.todoist.list.model.TodoList;
import com.todoist.list.service.TodolistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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



    @GetMapping("/search/{name}")
    public ResponseEntity searchlist(@PathVariable("name") String list) {
        try {


            List<TodoList> todolistresult = todolistService.searchlistbyname(list);
           System.out.println(todolistresult);


            if(todolistresult.isEmpty()){



                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No Results");
            }
            else {

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(todolistresult);
            }
        } catch (Exception e) {

System.out.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());

        }
    }
















}
