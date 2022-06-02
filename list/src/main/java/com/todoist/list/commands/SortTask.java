
package com.todoist.list.commands;

import com.mongodb.client.MongoCollection;
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


        Document res = (Document) taskCollection.find();

            if(order=="asc") {
                res = (Document) taskCollection.find().sort(Sorts.ascending(sort));
            }

        if(order=="desc") {
           res = (Document) taskCollection.find().sort(Sorts.descending(sort));
            }


        return res.toJson();
    }


}


