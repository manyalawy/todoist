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

public class AddCollaborator implements Command{

    ObjectId userId;
    ObjectId todoListId;

    public AddCollaborator(String userId, String todoListId) {
        this.userId = new ObjectId(userId);
        this.todoListId = new ObjectId(todoListId);
    }

    public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection todolistCollection =  db.dbInit(CollectionNames.TODOLIST.get());


        Bson filter = Filters.eq("_id", this.todoListId);
        Bson update = Updates.push("collaborators", this.userId);
        todolistCollection.findOneAndUpdate(filter, update);
    }

}
