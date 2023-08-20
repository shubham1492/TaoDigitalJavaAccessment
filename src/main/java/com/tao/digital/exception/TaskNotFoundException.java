package com.tao.digital.exception;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(String error){
        super(error);
    }
}
