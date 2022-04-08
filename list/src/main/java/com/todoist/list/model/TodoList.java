package com.todoist.list.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TodoList {
    @Id
    String id;
    String name;
    List<Task> tasks;
    List<String> collaborators;
    String creator;

    public TodoList(String name) {
        this.name = name;
    }
}

