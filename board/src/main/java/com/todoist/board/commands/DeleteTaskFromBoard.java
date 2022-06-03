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
 
    String task_name;
    ObjectId board_ID;
    
    public DeleteTaskFromBoard(String taskName, String todoListId) {
        this.task_name = taskName;
        this.board_ID = new ObjectId(todoListId);
    }

   public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection todolistCollection =  db.dbInit(CollectionNames.TODOLIST.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());


        //Delete Task
        taskCollection.deleteOne(Filters.eq("name", task_name)); 

        //Deleting task from board
        Bson filter = Filters.eq("_id", this.board_ID);
        Bson update = Updates.pull("tasks", Filters.eq("name", task_name));
        todolistCollection.findOneAndUpdate(filter, update);

    }
    
    }

