package com.todoist.list.service;

import com.todoist.list.model.Task;
import com.todoist.list.model.TodoList;
import com.todoist.list.repo.TaskRepo;
import com.todoist.list.repo.TodoListRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Optional<Task> gettaskid(String id) {

        return taskRepo.findById(id);

    }


    public Task assigntask(Optional<Task> task , String name ) {
        Task _task=task.get();
        List<String> assignee = _task.getAssignee();
        if(assignee==null){
            List<String> newlist = new ArrayList<>();
            newlist.add(name);
            _task.setAssignee(newlist);
        }
        else {
            assignee.add(name);
            _task.setAssignee(assignee);
        }
        return taskRepo.save(_task);

    }

    public Task updatetask(Optional<Task> task , Task tasknew ) {
        Task _task=task.get();

        if(tasknew.getPriority() != null){
            _task.setPriority(tasknew.getPriority());
        }
        if(tasknew.getDone() != null ){
            _task.setDone(tasknew.getDone());
        }
        if(tasknew.getDue_date() != null ){
            _task.setDue_date(tasknew.getDue_date());
        }
        return taskRepo.save(_task);
    }
    public ResponseEntity deleteTask(String list_id,String task_id){
        if (!taskRepo.existsById(task_id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("cannot find task id");
        }
        Optional<TodoList> todoList = todoListRepo.findById(list_id);
        if(todoList.isPresent()) {
            TodoList list = todoList.get();
            for (int i = 0; i < list.getTasks().size(); i++) {
                if (list.getTasks().get(i).getId().equals(task_id)) {
                    list.getTasks().remove(i);
                }
            }
            todoListRepo.save(list);
        }
        taskRepo.deleteById(task_id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Task "+task_id+"is deleted successfuly");
    }
















}
