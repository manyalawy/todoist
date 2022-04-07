package com.todoist.list.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document
public class Subtask {
    @Id
    String id;
    String name;
    Boolean done;
    LocalDate due_date;
    List<Comment> comments;
    int priority;

    public Subtask(String name, Boolean done, LocalDate due_date, List<Comment> comments, int priority) {
        this.name = name;
        this.done = done;
        this.due_date = due_date;
        this.comments = comments;
        this.priority = priority;
    }
}
