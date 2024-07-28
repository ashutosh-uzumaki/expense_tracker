package com.ashutosh.expense_tracker.custom_exceptions;

import com.ashutosh.expense_tracker.entity.ErrorObject;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExpenseNotFound(ResourceNotFoundException expenseNotFound, WebRequest request){
        ErrorObject errObj = new ErrorObject();
        errObj.setStatusCode(HttpStatus.NOT_FOUND.value());
        errObj.setMessage(expenseNotFound.getMessage());
        errObj.setTimeStamp(new Date());
        return new ResponseEntity<>(errObj, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorObject> handleTypeMisMatch(MethodArgumentTypeMismatchException typeMisMatchExcpetion, WebRequest request){
        ErrorObject errObj = new ErrorObject();
        errObj.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errObj.setMessage(typeMisMatchExcpetion.getMessage());
        errObj.setTimeStamp(new Date());
        return new ResponseEntity<>(errObj, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(Exception e, WebRequest request){
        ErrorObject errObj = new ErrorObject();
        errObj.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errObj.setMessage(e.getMessage());
        errObj.setTimeStamp(new Date());
        return new ResponseEntity<>(errObj, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
