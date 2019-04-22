package com.example.introsliderapp.model;

public final class Admin {

    //static single instance of the class
    private static Admin admin = null;

    private String email;
    private String password;

    private Admin(String email,String password){
        this.email = email;
        this.password = password;
    }

    public static Admin createObject(String email, String password){
        if(admin == null){
            admin = new Admin(email,password);
        }
        else{
            System.out.println("This is a singleton class, there can be only one admin");
        }
        return admin;
    }

}
