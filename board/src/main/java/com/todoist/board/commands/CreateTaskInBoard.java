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

public class CreateTaskInBoard implements Command {
    String task_name;
    ObjectId board_ID;

    public CreateTaskInBoard(String taskName, String boardID) {
        this.task_name = taskName;
        this.board_ID = new ObjectId(boardID);
    }

    public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection todolistCollection =  db.dbInit(CollectionNames.BOARD.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());

        //Inserting task
        Document task = new Document("name", this.task_name);
        InsertOneResult result = taskCollection.insertOne(task);

        //Inserting task in board
        Bson filter = Filters.eq("_id", this.board_ID);
        Bson update = Updates.push("tasks", result.getInsertedId().asObjectId());
        todolistCollection.findOneAndUpdate(filter, update);
    }
}
