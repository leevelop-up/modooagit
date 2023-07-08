package com.example.modooagit.service;

public class PasswordWrongException extends RuntimeException{
    PasswordWrongException(){
        super("password is wrong");
    }
}
