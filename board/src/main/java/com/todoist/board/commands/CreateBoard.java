package com.todoist.list.commands;

import com.mongodb.client.MongoCollection;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class CreateBoard implements Command {
    String BoardName;
    String userId;
    ArrayList <String> creators;

    public CreateBoard(String BoardName, String userId) {
        this.BoardName = BoardName;
        this.userId = userId;
        this.creators = new ArrayList<>();
        this.creators.add(userId);
    }

    public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection collection =  db.dbInit(CollectionNames.BOARD.get());
        Document board = new Document("name", this.BoardName).append("creator", this.userId).append("collaborators", this.creators);
        collection.insertOne(board);
    }
}
