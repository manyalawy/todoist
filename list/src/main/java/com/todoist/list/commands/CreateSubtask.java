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

public class CreateSubtask implements Command{

    String subtaskName;
    ObjectId taskId;

    public CreateSubtask(String subtaskName, ObjectId taskId) {
        this.subtaskName = subtaskName;
        this.taskId = taskId;
    }

    public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection taskCollection =  db.dbInit(CollectionNames.TASK.get());
        MongoCollection subTaskCollection = db.dbInit(CollectionNames.SUBTASK.get());

        //Inserting task
        Document subtask = new Document("name", this.subtaskName);
        InsertOneResult result = subTaskCollection.insertOne(subtask);

        //Inserting task in todolist
        Bson filter = Filters.eq("_id", this.taskId);
        Bson update = Updates.push("subtasks", result.getInsertedId().asObjectId());
        taskCollection.findOneAndUpdate(filter, update);
    }

}
