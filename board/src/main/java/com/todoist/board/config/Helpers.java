package com.todoist.list.config;

import com.todoist.list.commands.CreateTodolist;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Helpers {
    public Helpers() {
    }

    public JSONObject parseToJson(String message){
        JSONParser parser = new JSONParser();
        try {
            JSONObject json = (JSONObject) parser.parse(message);
            return json;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
