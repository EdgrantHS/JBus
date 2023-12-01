package com.edgrantJBusRD;

import com.edgrantJBusRD.Renter;
import com.edgrantJBusRD.dbjson.Serializable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account extends Serializable
{
    private final static String REGEX_EMAIL = "^[a-zA-Z0-9]+@[a-zA-Z_]+?\\.[a-zA-Z.]+[a-zA-Z]+$";
    private final static String REGEX_PASSWORD = "^( =.*[a-z])( =.*[A-Z])( =.*\\d)[a-zA-Z\\d]{8,}$";
    public String email, name, password;
    public Renter company;
    public double balance;

    public Account(String name, String email, String password)
    {
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = 0;
        this.company = null;
    }

    public String toString(){
        return (
            "" + 
            super.id + " " +
            this.name + " " +
            this.email + " " +
            this.password
        );
    }
    
    public Object write(){
        return this;
    }
    
    public boolean read(String content){
        return false;
    }

    public boolean validate(){
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(this.email);
        boolean name = matcher.find();
        pattern = Pattern.compile(REGEX_PASSWORD);
        matcher = pattern.matcher(this.password);
        boolean phone = matcher.find();
        return name && phone;
    }
}
