package com.todoist.list.commands;

import com.mongodb.client.result.InsertOneResult;

public interface Command {

    public Object execute();


}


