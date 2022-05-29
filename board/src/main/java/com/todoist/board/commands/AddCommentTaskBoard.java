package com.todoist.board.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class AddCommentTaskBoard implements Command{

    String content;
    ObjectId task_ID;

    public AddCommentTaskBoard(String comment, String task_ID) {
        this.content = comment;
        this.task_ID = new ObjectId(task_ID);

    }

    public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection taskCollection =  db.dbInit(CollectionNames.TASK.get());
        MongoCollection commentCollection = db.dbInit(CollectionNames.COMMENT.get());


        Document comment = new Document("content", this.content);
        InsertOneResult result = commentCollection.insertOne(comment);


        Bson filter = Filters.eq("_id", this.task_ID);

        Bson update = Updates.push("comments", result.getInsertedId().asObjectId());
        taskCollection.findOneAndUpdate(filter, update);
    }

}