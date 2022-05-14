package commands;

import com.mongodb.client.MongoCollection;
import config.MongoDB;
import constants.CollectionNames;
import org.bson.Document;

public class CreateTodolist implements Command {
    String todolistName;
    public CreateTodolist(String todolistName){
        this.todolistName = todolistName;
    }
    public void execute() {
        MongoDB db = new MongoDB();
        MongoCollection collection =  db.dbInit(CollectionNames.TODOLIST.get());
        Document todolist = new Document("name", this.todolistName);
        collection.insertOne(todolist);
    }
}
