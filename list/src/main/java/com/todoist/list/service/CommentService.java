package com.todoist.list.service;

import com.todoist.list.model.Comment;
import com.todoist.list.model.Task;
import com.todoist.list.model.TodoList;
import com.todoist.list.repo.CommentRepo;
import com.todoist.list.repo.TaskRepo;
import com.todoist.list.repo.TodoListRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentService {
    final private CommentRepo commentRepo;
    private final TaskRepo taskRepo;
    private String idCumulative = "0";

    public Map createComment(String commentBody,String task_id,String user_id){
        Comment comment = new Comment(idCumulative,user_id,commentBody, LocalDateTime.now());
        Optional<Task> task = taskRepo.findById(task_id);
            if(task.isPresent()){
                Task taskPrim = task.get();
                if(taskPrim.getComments() == null){
                    taskPrim.setComments(new ArrayList<>());
                }
                taskPrim.getComments().add(comment);
                commentRepo.insert(comment);
                taskRepo.save(taskPrim);
            }
            Map result = new HashMap<String, Object>();
            result.put("success", true);
            result.put("data", comment);
            return result;
        }
    }

