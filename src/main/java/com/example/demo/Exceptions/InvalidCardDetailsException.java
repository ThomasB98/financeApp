package com.example.demo.Exceptions;

public class InvalidCardDetailsException extends RuntimeException{
    public InvalidCardDetailsException(String message){
        super(message);
    }
}
