package com.studentmanagementsystem.api.exception;

public class StudentNotFoundException extends RuntimeException{

    private static final long serialVersionUId = 1L;

    public StudentNotFoundException(String message){
        super(message);
    }

}
