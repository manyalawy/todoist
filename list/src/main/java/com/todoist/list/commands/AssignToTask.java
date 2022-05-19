
package com.todoist.list.commands;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class AssignToTask implements Command{

    String assignee;
    String TaskId;

    public AssignToTask(String TaskId, String assignee) {
        this.TaskId = TaskId;
        this.assignee = assignee;
    }


    @Override
    public void execute() {


        MongoDB db = new MongoDB();
        // MongoCollection todolistCollection =  db.dbInit(CollectionNames.TODOLIST.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());



        //assigne not repeated


        Bson filter = Filters.eq("_id", this.TaskId);

        FindIterable test = taskCollection.find(filter);
        Bson update = Updates.push("assignee", this.assignee);
        taskCollection.findOneAndUpdate(filter, update);
//



    }
}


