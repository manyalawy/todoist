package com.todoist.list.service;

import com.todoist.list.repo.TaskRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TaskService {
    private final TaskRepo taskRepo;
}
