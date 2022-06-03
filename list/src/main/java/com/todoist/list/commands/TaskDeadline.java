
package com.todoist.list.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.time.LocalDate;

public class TaskDeadline implements Command{




    public TaskDeadline() {



    }




    @Override
    public String execute() {

        MongoDB db = new MongoDB();
        MongoCollection todolistCollection =  db.dbInit(CollectionNames.TODOLIST.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());


        Bson filter = Filters.gt("due_date",LocalDate.now());
        MongoCursor res = taskCollection.find(filter).iterator();
        String result = "";
        while(res.hasNext()){
            result += res.next().toString();
        }

        return result;
//       todolistCollection.find(filter).forEach(doc -> System.out.println(doc));

    }


}


