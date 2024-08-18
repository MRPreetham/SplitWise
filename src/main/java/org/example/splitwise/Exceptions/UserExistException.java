package org.example.splitwise.Exceptions;

public class UserExistException extends Exception {
    public UserExistException(String message){
        super(message);
    }
}
