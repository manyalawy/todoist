package com.todoist.list.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.todoist.list.constants.CollectionNames;
import org.bson.Document;

public class MongoDB {

    public MongoDB(){

    }

    public MongoCollection dbInit(String collectionName){
        String uri = "mongodb://rootuser:rootpass@localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("List");
        MongoCollection collection = database.getCollection(collectionName);
        return collection;
    }

    public static void main(String[] args) {
        MongoDB db = new MongoDB();
        MongoCollection collection = db.dbInit(CollectionNames.TODOLIST.get());
        Document document = new Document("name", "list1");
        collection.insertOne(document);

    }


}
