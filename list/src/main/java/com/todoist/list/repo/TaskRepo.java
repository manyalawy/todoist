package com.todoist.list.repo;

import com.todoist.list.model.Task;
import com.todoist.list.model.TodoList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TaskRepo extends MongoRepository<Task, String> {

   // @Query("{ 'due_date' : {'$lt': {'$date' :due_date}}   }")
    List<Task> findByName (String name) ;
    List<Task> findByDuedateAfter(LocalDate due_date);
    List<Task> findAllByOrderByNameAsc();
    List<Task> findAllByOrderByNameDesc();
    List<Task> findAllByOrderByDuedateAsc();
    List<Task> findAllByOrderByDuedateDesc();
    List<Task> findAllByOrderByPriorityAsc();
    List<Task> findAllByOrderByPriorityDesc();
    List<Task> findAllByOrderByDateaddedAsc();
    List<Task> findAllByOrderByDateaddedDesc();




}
