package com.todoist.list.controller;

import com.todoist.list.model.Task;
import com.todoist.list.model.TodoList;
import com.todoist.list.service.TaskService;
import com.todoist.list.service.TodolistService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/todolist")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TodolistService todolistService;

    @PostMapping("task/create/{list_id}")
    public Map createTask(@RequestBody Task task, @PathVariable("list_id") String list_id){
        Map result = taskService.createTask(task, list_id);
        return result ;
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity editlist(@PathVariable("id") String id , @RequestBody Task newtask ) {
        try {

            Optional<Task> taskedit =  taskService.gettaskid(id);



            if (taskedit.isPresent()) {

                Task taskeupdated=taskService.updatetask(taskedit,newtask);



                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(taskeupdated);
            }
            else{


                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("cannot find task id");
            }




        } catch (Exception e) {


            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());

        }
    }

    @PutMapping("/assignee/add/{name}")
    public ResponseEntity assigntotask( @PathVariable("name") String name, @RequestBody Task taskid  ) {
        try {



            // System.out.println();



            Optional<Task> _task =  taskService.gettaskid(taskid.getId());

            if(_task.isEmpty()){



                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Cannot find id");
            }

            Task taskeupdated=taskService.assigntask(_task,name);


            // collabroter id

            // check it in the collabroter exits


            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(taskeupdated);



        } catch (Exception e) {

            //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());

        }
    }

    @GetMapping("/task/getUpcomingDeadlines")
    public ResponseEntity getUpcomingDeadlines() {
        try {


            List<Task> taskResults = taskService.getUpcomingDeadlines();
            System.out.println(taskResults);

            if(taskResults.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No Results");
            }
            else {
                System.out.println("HERE");

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(taskResults);
            }
        } catch (Exception e) {

            System.out.println("HERE");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());

        }
    }

    @GetMapping("/task/sort/{sortby}/{type}")
    public ResponseEntity Sortby( @PathVariable("sortby") String sortby, @PathVariable("type") String type) {
        try {
            System.out.println("sort by"+sortby == "name");

            List<Task> taskResults = new ArrayList<Task>();
            if (sortby.equals("name")){
                if (type.equals("asc")){
                    taskResults = taskService.SortbyNameAsc();
                }
                else{
                    taskResults = taskService.SortbyNameDsc();

                }
            }
            else if (sortby.equals("due_date")){
                if (type.equals("asc")){
                    taskResults = taskService.SortbyDue_dateAsc();
                }
                else{
                    taskResults = taskService.SortbyDue_dateDsc();

                }
            }
            else if (sortby.equals("priority")){
                if (type.equals("asc")){
                    taskResults = taskService.SortbyPriorityAsc();
                }
                else{
                    taskResults = taskService.SortbyPriorityDsc();

                }
            }
            else if (sortby.equals("date_added")){
                if (type.equals("asc")){
                    taskResults = taskService.SortbyDateaddedAsc();
                }
                else{
                    taskResults = taskService.SortbyDateaddedDsc();

                }
            }
            System.out.println("taskResukts"+taskResults);
            if(taskResults.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No Results");
            }
            else {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(taskResults);
            }
        } catch (Exception e) {

            System.out.println("HERE");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());

        }
    }

    @GetMapping("/task/search/{name}")
    public ResponseEntity Sortby( @PathVariable("name") String name) {
        try {

            List<Task> taskResults = taskService.searchlistbyname(name);

            if(taskResults.isEmpty()){
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No Results");
            }
            else {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(taskResults);
            }
        } catch (Exception e) {

            System.out.println("HERE");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());

        }
    }



















}
