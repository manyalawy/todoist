package com.example.demo.user;

import java.util.HashMap;

public abstract class Command {

    public abstract String execute(HashMap<String ,Object> map) throws Exception;
}
