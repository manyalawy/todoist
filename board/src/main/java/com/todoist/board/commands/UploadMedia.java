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
public class UploadMedia {
    Binary img;
    ObjectId board_ID;

    public UploadMedia(MultipartFile img, String board_id) {
        this.img = new Binary(BsonBinarySubType.BINARY, img.getBytes());
        this.board_id = new ObjectId(board_id); 
    }

    public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection boardCollection =  db.dbInit(CollectionNames.BOARD.get());
        MongoCollection commentCollection = db.dbInit(CollectionNames.PHOTO.get());


        Document image = new Document("content", this.content);
        InsertOneResult result = commentCollection.insertOne(img);


        Bson filter = Filters.eq("_id", this.board_ID);

        Bson update = Updates.push("comments", result.getInsertedId().asObjectId());
        boardCollection.findOneAndUpdate(filter, update);
    }
 
}
