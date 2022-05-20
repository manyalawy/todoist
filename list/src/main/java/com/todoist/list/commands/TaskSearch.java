
package com.todoist.list.commands;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class TaskSearch implements Command{

    String taskName;
    ObjectId Listid;


    public TaskSearch(String Listid,String taskName) {
        this.taskName = taskName;
        this.Listid = new ObjectId(Listid);

    }




    @Override
    public void execute() {

        MongoDB db = new MongoDB();
        MongoCollection todolistCollection =  db.dbInit(CollectionNames.TODOLIST.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());


        //Inserting task in todolist
        Bson filter = Filters.eq("name", taskName);
        Bson filter2 = Filters.eq("_id", Listid);

//         Bson projection = Projections.include("name");



        taskCollection.find(filter);

//       todolistCollection.find(filter).forEach(doc -> System.out.println(doc));



    }


}


