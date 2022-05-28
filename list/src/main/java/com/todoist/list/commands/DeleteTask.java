package com.todoist.list.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class DeleteTask implements Command {
    ObjectId taskId;
    ObjectId todoListId;

    public DeleteTask(String taskId, String todoListId) {
        this.taskId = new ObjectId(taskId);
        this.todoListId = new ObjectId(todoListId);
    }

    public InsertOneResult execute() {
        MongoDB db = new MongoDB();
        MongoCollection todolistCollection =  db.dbInit(CollectionNames.TODOLIST.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());


        Bson query = Filters.eq("_id", this.taskId);
        DeleteResult result = taskCollection.deleteOne(query);


        Bson filter = Filters.eq("_id", this.todoListId);
        Bson update = Updates.pull("tasks", this.taskId);
        todolistCollection.findOneAndUpdate(filter, update);
        return null;
    }
}
