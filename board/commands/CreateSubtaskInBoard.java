package com.todoist.board.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.todoist.board.config.MongoDB;
import com.todoist.board.constants.CollectionNames;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class CreateSubtaskInBoard implements Command{

    String subtask_name;
    ObjectId board_ID;

    public CreateSubtaskInBoard(String subtaskName, String taskId) {
        this.subtask_name = subtaskName;
        this.board_ID = new ObjectId(taskId);
    }

    public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection taskCollection =  db.dbInit(CollectionNames.TASK.get());
        MongoCollection subTaskCollection = db.dbInit(CollectionNames.SUBTASK.get());

        //Inserting task
        Document subtask = new Document("name", this.subtask_name);
        InsertOneResult result = subTaskCollection.insertOne(subtask);

        //Inserting task in todolist
        Bson filter = Filters.eq("_id", this.board_ID);
        Bson update = Updates.push("subtasks", result.getInsertedId().asObjectId());
        taskCollection.findOneAndUpdate(filter, update);
    }

}
