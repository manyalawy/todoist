package com.example.demo.user;

import java.util.HashMap;

public class AddUserCommand extends UserCommand{

    @Override
    public String execute(HashMap<String,Object> map){

        return getUserService().addNewUser((User) map.get("user"));

    }
}
