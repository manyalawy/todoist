package com.todoist.list.constants;

public enum CollectionNames {
    TODOLIST("todolist"),
    TASK("task"),
    SUBTASK("subtask"),
    COMMENT("comment");
    BOARD("board");

    String collectionName;
    CollectionNames(String collectionName) {
        this.collectionName = collectionName;
    }

    public String get() {
        return this.collectionName;
    }
}
