package com.ashutosh.expense_tracker.custom_exceptions;

public class ItemAlreadyExistsException extends RuntimeException{
    private static final long serialVersionId = 1l;

    public ItemAlreadyExistsException(String message){
        super(message);
    }
}
