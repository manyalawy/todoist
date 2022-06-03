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

public class AddComment implements Command{

    String content;
    ObjectId taskId;

    public AddComment(String comment, String taskId) {
        this.content = comment;
        this.taskId = new ObjectId(taskId);
    }

    public String execute() {
        MongoDB db = new MongoDB();
        MongoCollection taskCollection =  db.dbInit(CollectionNames.TASK.get());
        MongoCollection commentCollection = db.dbInit(CollectionNames.COMMENT.get());


        Document comment = new Document("content", this.content);
        InsertOneResult result = commentCollection.insertOne(comment);


        Bson filter = Filters.eq("_id", this.taskId);
        Bson update = Updates.push("comments", result.getInsertedId().asObjectId());
        Document res = (Document)   taskCollection.findOneAndUpdate(filter, update);

        return res.toJson();
        //return result;
    }

}
