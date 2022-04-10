package com.todoist.list.service;

import com.todoist.list.model.Task;
import com.todoist.list.model.TodoList;
import com.todoist.list.repo.TaskRepo;
import com.todoist.list.repo.TodoListRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
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
            task.setDateadded(LocalDate.now());
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

        if(tasknew.getDuedate() != null ){

            _task.setDuedate(tasknew.getDuedate());
        }



        return taskRepo.save(_task);
    }

    public List<Task>  getUpcomingDeadlines() {
        System.out.println("whwhwh"+LocalDate.now());
        Date date = new Date();
        List<Task> taskresults = taskRepo.findByDuedateAfter(LocalDate.now());
        return taskresults;

    }

    public List<Task>  SortbyNameAsc( ){
        List<Task> taskresults = taskRepo.findAllByOrderByNameAsc();
        return taskresults;

    }
    public List<Task>  SortbyNameDsc() {
        List<Task> taskresults = taskRepo.findAllByOrderByNameDesc();
        return taskresults;

    }
    public List<Task>  SortbyDue_dateAsc() {
        // Sort sort  = Sort.by(sortby);
        List<Task> taskresults = taskRepo.findAllByOrderByDuedateAsc();
        return taskresults;

    }
    public List<Task>  SortbyDue_dateDsc() {
        // Sort sort  = Sort.by(sortby);
        List<Task> taskresults = taskRepo.findAllByOrderByDuedateDesc();
        return taskresults;

    }
    public List<Task>  SortbyPriorityAsc() {
        // Sort sort  = Sort.by(sortby);
        List<Task> taskresults = taskRepo.findAllByOrderByPriorityAsc();
        return taskresults;

    }
    public List<Task>  SortbyPriorityDsc() {
        // Sort sort  = Sort.by(sortby);
        List<Task> taskresults = taskRepo.findAllByOrderByPriorityDesc();
        return taskresults;

    }
    public List<Task>  SortbyDateaddedAsc() {
        List<Task> taskresults = taskRepo.findAllByOrderByDateaddedAsc();
        return taskresults;

    }
    public List<Task>  SortbyDateaddedDsc() {
        List<Task> taskresults = taskRepo.findAllByOrderByDateaddedDesc();
        return taskresults;

    }


    public List<Task>  searchlistbyname(String name) {
        List<Task> taskresults = taskRepo.findByName(name);
        return taskresults;

    }











}
