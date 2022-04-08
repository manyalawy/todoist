package com.todoist.list.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Document
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Task {
    @Id
    String id;
    String name;
    Boolean done;
    List<Subtask> subtasks;
    List<String> assignee;
    String priority;
    LocalDate due_date;
    LocalDate date_added;
    List<Comment> comments;
}
