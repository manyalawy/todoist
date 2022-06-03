package com.todoist.list.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class CreateTask implements Command {
    String taskName;
    ObjectId todoListId;

    public CreateTask(String taskName, String todoListId) {
        this.taskName = taskName;
        this.todoListId = new ObjectId(todoListId);
    }

    public InsertOneResult execute() {
        MongoDB db = new MongoDB();
        MongoCollection todolistCollection =  db.dbInit(CollectionNames.TODOLIST.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());

        //Inserting task
        Document task = new Document("name", this.taskName);
        InsertOneResult result = taskCollection.insertOne(task);

        //Inserting task in todolist
        Bson filter = Filters.eq("_id", this.todoListId);
        Bson update = Updates.push("tasks", result.getInsertedId().asObjectId());
        todolistCollection.findOneAndUpdate(filter, update);
        return result;
    }
}
