package ru.icl.test.auth;

import java.util.Random;

public class username {
    private String name;
    
    public username() {
        Random r = new Random();        
        int x = r.nextInt(100)+1;
        String str = Integer.toString(x);
        this.name = "Guest".concat(str);       
    }
    
    public String getName(){
        return name;
    }
}
