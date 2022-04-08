package com.todoist.list.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document
@AllArgsConstructor
@Getter
@Setter
@ToString
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
