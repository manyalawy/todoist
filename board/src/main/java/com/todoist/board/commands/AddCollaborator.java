package main.java.com.todoist.board.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.todoist.board.config.MongoDB;
import com.todoist.board.constants.CollectionNames;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class AddCollaborator implements Command {
    ObjectId user_ID;
    ObjectId board_ID;

    public AddCollaborator(ObjectId user_ID, String todoListId) {
        this.user_ID = user_ID;
        this.board_ID = new ObjectId(todoListId);
    }

    public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection todolistCollection =  db.dbInit(CollectionNames.TODOLIST.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());

        //Inserting user
        Document user = new Document("user_id", this.user_ID);
        InsertOneResult result = taskCollection.insertOne(user);

        //Inserting user in board
        Bson filter = Filters.eq("_id", this.board_ID);
        Bson update = Updates.push("users", result.getInsertedId().asObjectId());
        todolistCollection.findOneAndUpdate(filter, update);
    }
}
