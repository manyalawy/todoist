package com.todoist.board.constants;

public enum CollectionNames {
    TODOLIST("todolist"),
    TASK("task"),
    SUBTASK("subtask"),
    COMMENT("comment");

    String collectionName;
    CollectionNames(String collectionName) {
        this.collectionName = collectionName;
    }

    public String get() {
        return this.collectionName;
    }
}
