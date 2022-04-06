package com.todoist.list.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;

public class Task {
    @Id
    String id;
    Boolean done;
    List<Subtask> subtasks;
    List<String> assignee;
    int priority;
    LocalDate due_date;
    LocalDate date_added;
    List<Comment> comments;
}
