
package com.todoist.list.commands;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.InsertOneResult;
import com.todoist.list.config.MongoDB;
import com.todoist.list.constants.CollectionNames;
import org.bson.Document;
import org.bson.conversions.Bson;

public class ListSearch implements Command{

    String listName;


    public ListSearch(String listName) {
        this.listName = listName;

    }




    @Override
    public String execute() {

        MongoDB db = new MongoDB();
        MongoCollection todolistCollection =  db.dbInit(CollectionNames.TODOLIST.get());


        //Inserting task in todolist
        Bson filter = Filters.eq("name", listName);

//        Bson projection = Projections.include("name");
        Document res = (Document)      todolistCollection.find(filter);

        return res.toJson();
    }


}


