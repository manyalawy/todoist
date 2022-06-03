package com.example.demo.user;

import java.util.HashMap;

public class deleteUserCommand extends UserCommand {
    @Override
    public String execute(HashMap<String,Object> map){

        return  getUserService().deleteUser((Long)map.get("id"));
    }
}
