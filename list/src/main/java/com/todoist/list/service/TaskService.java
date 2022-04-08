package com.todoist.list.service;

import com.todoist.list.model.Task;
import com.todoist.list.model.TodoList;
import com.todoist.list.repo.TaskRepo;
import com.todoist.list.repo.TodoListRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Service
public class TaskService {
    private final TaskRepo taskRepo;
    private final TodoListRepo todoListRepo;

    public Map createTask(Task task, String list_id) {
        Optional<TodoList> todoList = todoListRepo.findById(list_id);
        if(todoList.isPresent()){
            TodoList list = todoList.get();
            if(list.getTasks() == null){
                list.setTasks(new ArrayList<>());
            }
            list.getTasks().add(task);
            task.setDate_added(LocalDate.now());
            taskRepo.insert(task);
            todoListRepo.save(list);
        }
        Map result = new HashMap<String, Object>();
        result.put("success", true);
        result.put("data", task);
        return result;
    }

}
