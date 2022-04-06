package com.todoist.list.repo;

import com.todoist.list.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepo extends MongoRepository<Task, String> {}
