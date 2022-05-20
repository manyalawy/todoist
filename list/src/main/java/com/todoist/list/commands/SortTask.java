
package com.todoist.list.commands;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.awt.*;
import java.util.Date;

public class SortTask implements Command{

    String sort;
    ObjectId Taskid;
    String order;


    public SortTask(String Taskid, String sort, String order) {
        this.sort = sort;
        this.Taskid = new ObjectId(Taskid);
        this.order=order;


    }




    @Override
    public void execute() {

        MongoDB db = new MongoDB();
        MongoCollection todolistCollection =  db.dbInit(CollectionNames.TODOLIST.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());


            if(order=="asc") {
                taskCollection.find().sort(Sorts.ascending(sort));
            }

            else{
                taskCollection.find().sort(Sorts.descending(sort));
            }

//       todolistCollection.find(filter).forEach(doc -> System.out.println(doc));



    }


}


