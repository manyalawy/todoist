package com.todoist.list.service;

import com.todoist.list.repo.SubtaskRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SubtaskService {
    private final SubtaskRepo subtaskRepo;
}
