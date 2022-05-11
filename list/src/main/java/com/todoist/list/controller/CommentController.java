package com.todoist.list.controller;

import com.todoist.list.model.Comment;
import com.todoist.list.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "api/todolist")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("task/{task_id}/comment/create")
    public Map comment(@RequestBody String comment,@RequestBody String user_id,@PathVariable("task_id")String task_id){
        Map result = commentService.createComment(comment,task_id,user_id);
        return result ;
    }
}
