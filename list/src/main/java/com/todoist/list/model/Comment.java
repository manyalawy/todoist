package com.todoist.list.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Comment {
    @Id
    String id;
    String author;
    String content;
    LocalDateTime timestamp;
}
