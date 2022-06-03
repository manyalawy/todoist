package com.boards.commands;

import java.io.IOException;

import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.internal.operation.FindAndUpdateOperation;

import org.bson.BsonBinarySubType;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.web.multipart.MultipartFile;
public class UploadMedia {
    String img;
    ObjectId board_id;

    public UploadMedia(String img, String boardID) throws IOException {
        this.img = img;
        this.board_id = new ObjectId(boardID); 
    }

    public FindAndUpdateOperation execute() {
        MongoDB db = new MongoDB();
        MongoCollection boardCollection =  db.dbInit(CollectionNames.BOARD.get());
        MongoCollection commentCollection = db.dbInit(CollectionNames.PHOTO.get());


        
        Document image = new Document("content", this.img);
        InsertOneResult result = commentCollection.insertOne(img);


        Bson filter = Filters.eq("_id", this.board_id);

        Bson update = Updates.push("comments", result.getInsertedId().asObjectId());
        FindAndUpdateOperation res =  (FindAndUpdateOperation) boardCollection.findOneAndUpdate(filter, update);
       return res;
    }
 
}
