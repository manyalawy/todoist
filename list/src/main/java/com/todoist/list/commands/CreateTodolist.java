package com.todoist.list.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.Document;

import java.util.ArrayList;

public class CreateTodolist implements Command {
    String todolistName;
    String userId;
    ArrayList <String> creators;

    public CreateTodolist(String todolistName, String userId) {
        this.todolistName = todolistName;
        this.userId = userId;
        this.creators = new ArrayList<>();
        this.creators.add(userId);
    }

    public InsertOneResult execute() {
        MongoDB db = new MongoDB();
        MongoCollection collection =  db.dbInit(CollectionNames.TODOLIST.get());
        Document todolist = new Document("name", this.todolistName).append("creator", this.userId).append("collaborators", this.creators);
        InsertOneResult result =  collection.insertOne(todolist);
        return result;
    }
}
