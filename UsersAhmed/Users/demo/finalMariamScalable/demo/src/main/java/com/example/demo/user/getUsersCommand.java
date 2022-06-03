package com.example.demo.user;

import java.util.HashMap;

public class getUsersCommand extends UserCommand{
    @Override
    public String execute(HashMap<String,Object> map){

        return  getUserService().getUsers();
    }
}
