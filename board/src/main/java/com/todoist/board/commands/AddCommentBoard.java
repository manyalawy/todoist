package main.java.com.todoist.board.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class AddCommentBoard implements Command{

    String content;
    ObjectId board_ID;

    public AddCommentTaskBoard(String comment, String board_id) {
        this.content = comment;
        this.board_id = new ObjectId(board_id);

    }

    public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection taskCollection =  db.dbInit(CollectionNames.TASK.get());
        MongoCollection commentCollection = db.dbInit(CollectionNames.COMMENT.get());


        Document comment = new Document("content", this.content);
        InsertOneResult result = commentCollection.insertOne(comment);


        Bson filter = Filters.eq("_id", this.board_ID);

        Bson update = Updates.push("comments", result.getInsertedId().asObjectId());
        taskCollection.findOneAndUpdate(filter, update);
    }

}