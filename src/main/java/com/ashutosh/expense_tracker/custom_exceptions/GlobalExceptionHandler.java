package com.ashutosh.expense_tracker.custom_exceptions;

import com.ashutosh.expense_tracker.entity.ErrorObject;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFound(ResourceNotFoundException expenseNotFound, WebRequest request){
        ErrorObject errObj = new ErrorObject();
        errObj.setStatusCode(HttpStatus.NOT_FOUND.value());
        errObj.setMessage(expenseNotFound.getMessage());
        errObj.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(errObj, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleTypeMisMatch(MethodArgumentTypeMismatchException typeMisMatchExcpetion, WebRequest request){
        ErrorObject errObj = new ErrorObject();
        errObj.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errObj.setMessage(typeMisMatchExcpetion.getMessage());
        errObj.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(errObj, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorObject> handleRunTimeException(RuntimeException re, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObject.setMessage(re.getMessage());
        errorObject.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ItemAlreadyExistsException.class)
    public ResponseEntity<ErrorObject> handleItemExistsException(ItemAlreadyExistsException itemAlreadyExistsException, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.CONFLICT.value());
        errorObject.setMessage(itemAlreadyExistsException.getMessage());
        errorObject.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.CONFLICT);
    }

}
