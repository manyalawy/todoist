package com.todoist.list.repo;

import com.todoist.list.model.TodoList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface TodoListRepo extends MongoRepository<TodoList, String> {

    List<TodoList> findByName (String name) ;


}
