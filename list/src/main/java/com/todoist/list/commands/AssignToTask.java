
package com.todoist.list.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class AssignToTask implements Command{

    String assignee;
    ObjectId TaskId;

    public AssignToTask(String TaskId, String assignee) {
        this.TaskId = new ObjectId(TaskId);
        this.assignee = assignee;
    }


    public String execute() {
        MongoDB db = new MongoDB();
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());
        Bson filter = Filters.eq("_id", TaskId);
        Bson update = Updates.push("assignee", assignee);
        Document res = (Document) taskCollection.findOneAndUpdate(filter, update);
        return res.toJson();
    }
}


