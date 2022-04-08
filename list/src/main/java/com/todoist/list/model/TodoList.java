package com.todoist.list.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @Indexed(unique=true)
    String name;
    List<Task> tasks;
    List<String> collaborators;
    String creator;
}

