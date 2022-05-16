//package com.todoist.list.controller;
//
//import com.todoist.list.model.Task;
//import com.todoist.list.model.TodoList;
//import com.todoist.list.service.TaskService;
//import com.todoist.list.service.TodolistService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//import java.util.Optional;
//
//@RestController
//@RequestMapping(path = "api/todolist")
//@AllArgsConstructor
//public class TaskController {
//    private final TaskService taskService;
//    private final TodolistService todolistService;
//
//    @PostMapping("task/create/{list_id}")
//    public Map createTask(@RequestBody Task task, @PathVariable("list_id") String list_id){
//        Map result = taskService.createTask(task, list_id);
//        return result ;
//    }
//
//
//    @PutMapping("/edit/{id}")
//    public ResponseEntity editlist(@PathVariable("id") String id , @RequestBody Task newtask ) {
//        try {
//
//            Optional<Task> taskedit =  taskService.gettaskid(id);
//
//
//
//            if (taskedit.isPresent()) {
//
//                Task taskeupdated=taskService.updatetask(taskedit,newtask);
//
//
//
//                return ResponseEntity
//                        .status(HttpStatus.OK)
//                        .body(taskeupdated);
//            }
//            else{
//
//
//                return ResponseEntity
//                        .status(HttpStatus.NOT_FOUND)
//                        .body("cannot find task id");
//            }
//
//
//
//
//        } catch (Exception e) {
//
//
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(e.getMessage());
//
//        }
//    }
//
//    @PutMapping("/assignee/add/{name}")
//    public ResponseEntity assigntotask( @PathVariable("name") String name, @RequestBody Task taskid  ) {
//        try {
//
//
//
//            // System.out.println();
//
//
//
//            Optional<Task> _task =  taskService.gettaskid(taskid.getId());
//
//            if(_task.isEmpty()){
//
//
//
//                return ResponseEntity
//                        .status(HttpStatus.NOT_FOUND)
//                        .body("Cannot find id");
//            }
//
//            Task taskeupdated=taskService.assigntask(_task,name);
//
//
//            // collabroter id
//
//            // check it in the collabroter exits
//
//
//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body(taskeupdated);
//
//
//
//        } catch (Exception e) {
//
//            //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//            return ResponseEntity
//                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(e.getMessage());
//
//        }
//    }
//
//

















//}
