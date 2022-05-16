package com.todoist.list.commands;

import com.mongodb.client.MongoCollection;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.Document;

public class CreateTodolist implements Command {
    String todolistName;
    public CreateTodolist(String todolistName){
        this.todolistName = todolistName;
    }
    public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection collection =  db.dbInit(CollectionNames.TODOLIST.get());
        Document todolist = new Document("name", this.todolistName);
        collection.insertOne(todolist);
    }
}
