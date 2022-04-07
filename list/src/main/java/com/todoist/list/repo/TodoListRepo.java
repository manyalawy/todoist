package com.todoist.list.repo;

import com.todoist.list.model.TodoList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoListRepo extends MongoRepository<TodoList, String> {
}
