package com.todoist.list.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    public MongoCollection dbInit(String collectionName){
        String uri = "mongodb://rootuser:rootpass@localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("List");
        MongoCollection collection = database.getCollection(collectionName);
        return collection;
    }


}
