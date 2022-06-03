package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserCommand extends Command{

    private userService userService;

    public userService getUserService(){
        return userService;
    }

    @Autowired
    public void setUserService(userService userService){
         this.userService= userService;
    }
}
