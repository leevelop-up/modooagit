package com.example.modooagit.service;

public class NameNotExistedException extends RuntimeException {

    NameNotExistedException(String name){
        super("name is no regist" + name);
    }


}
