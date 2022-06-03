package com.example.demo.user;

import java.util.HashMap;

public class updateUserCommand extends UserCommand{

    @Override
    public String execute(HashMap<String,Object> map){

        return  getUserService().updateUser((Long)map.get("id"),(String)map.get("name"),(String)map.get("email"),(String)map.get("password"));
    }
}
