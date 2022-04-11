package com.todoist.list.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Component
public class TodoList {
    @Id
    String id;
    @Indexed(unique=true)
    String name;
    @DBRef
    List<Task> tasks;
    List<String> collaborators;
    String creator;
}

