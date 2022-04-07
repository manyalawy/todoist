package com.todoist.list.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
public class TodoList {
    @Id
    String id;
    String name;
    List<Task> tasks;
    List<String> collaborators;
    String creator;
}

