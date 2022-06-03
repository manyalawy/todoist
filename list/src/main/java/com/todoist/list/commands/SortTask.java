
package com.todoist.list.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.Document;
import org.bson.types.ObjectId;

public class SortTask implements Command{

    String sort;
    ObjectId Taskid;
    String order;


    public SortTask(String sort, String order) {
        this.sort = sort;

        this.order=order;


    }




    @Override
    public String execute() {

        MongoDB db = new MongoDB();
        MongoCollection todolistCollection =  db.dbInit(CollectionNames.TODOLIST.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());


        String result = "";
            if(order=="asc") {

                MongoCursor <Document> res =  taskCollection.find().sort(Sorts.ascending(sort)).iterator();
                while(res.hasNext()){
                    result += res.next().toString();
                }
            }

        if(order=="desc") {
            MongoCursor <Document> res = taskCollection.find().sort(Sorts.descending(sort)).iterator();
            while(res.hasNext()){
                result += res.next().toString();
            }
            }






        return result;
    }


}


