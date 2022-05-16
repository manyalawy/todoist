//package com.todoist.list.controller;
//
//import com.todoist.list.model.Subtask;
//import com.todoist.list.model.Task;
//import com.todoist.list.service.SubtaskService;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping(path = "api/todolist")
//@AllArgsConstructor
//public class SubtaskController {
//    private final SubtaskService subtaskService;
//
//    @PostMapping("/subtask/create/{task_id}")
//    public Map createSubtask(@RequestBody Subtask subtask, @PathVariable("task_id") String task_id){
//       Map result = subtaskService.createSubtask(subtask, task_id);
//       return result;
//    }
//}
