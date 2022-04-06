package com.todoist.list.repo;

import com.todoist.list.model.Subtask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubtaskRepo extends MongoRepository<Subtask, String> {
}
