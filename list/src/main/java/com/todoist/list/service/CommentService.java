package com.todoist.list.service;

import com.todoist.list.repo.CommentRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CommentService {
    final private CommentRepo commentRepo;
}
