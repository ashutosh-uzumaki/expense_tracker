package com.ashutosh.expense_tracker.custom_exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionId = 1l;

    public ResourceNotFoundException(String message){
        super(message);
    }
}
