package com.boards.commands;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.boards.config.MongoDB;
import com.boards.constants.CollectionNames;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.FindIterable;


import java.time.LocalDate;

public class DeadlineTask implements Command{




    public DeadlineTask() {



    }




    @Override
    public String execute() {

        MongoDB db = new MongoDB();
        MongoCollection boardCollection =  db.dbInit(CollectionNames.BOARD.get());
        MongoCollection taskCollection = db.dbInit(CollectionNames.TASK.get());


        Bson filter = Filters.gt("due_date",LocalDate.now());
        MongoCursor res = taskCollection.find(filter).iterator();
        String result = "";
        while(res.hasNext()){
            result += res.next().toString();
        }

        return result;
    }


}