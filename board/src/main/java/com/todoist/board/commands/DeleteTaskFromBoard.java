package com.todoist.board.commands;

import java.util.Collections;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndDeleteOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.todoist.board.config.MongoDB;
import com.todoist.board.constants.CollectionNames;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;


public class DeleteTaskFromBoard implements Command{
 
    ObjectId task_ID;
    ObjectId board_ID;
    
    public DeleteTaskFromBoard(String taskID, String board_ID) {
        this.task_ID = taskID;
        this.board_ID = new ObjectId(board_ID);
    }

   public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection boardCollection =  db.dbInit(CollectionNames.BOARD.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());


        //Delete Task
        boardCollection.deleteOne(Filters.eq("_id", task_ID)); 

        //Deleting task from board
        Bson filter = Filters.eq("_id", this.board_ID);
        Bson update = Updates.pull("tasks", this.task_ID);
        boardCollection.findOneAndUpdate(filter, update);

    }
    
    }

