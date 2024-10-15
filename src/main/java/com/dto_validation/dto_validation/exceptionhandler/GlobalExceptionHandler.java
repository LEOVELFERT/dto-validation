package com.dto_validation.dto_validation.exceptionhandler;

import com.dto_validation.dto_validation.ErrorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ErrorMapper errorMapper;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> errorMessages=new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error-> addErrorMessageToMap(error, errorMessages));
        Map<String, Object> listOfErrors = errorMapper.getListOfErrors(errorMessages);
        return new ResponseEntity<>(listOfErrors, HttpStatus.BAD_REQUEST);
    }

    // Generic handler for all exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        // Create a generic error message
        String errorMessage = ex.getMessage();
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add("Error occurred: " + errorMessage);

        // Map errors (assuming errorMapper is used to format errors)
        Map<String, Object> listOfErrors = errorMapper.getListOfErrors(errorMessages);

        // Return a ResponseEntity with INTERNAL_SERVER_ERROR (500) status
        return new ResponseEntity<>(listOfErrors, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private static void addErrorMessageToMap(ObjectError error, List<String> errorMessages) {
        String fieldName;
        try{
            fieldName=((FieldError) error).getField();
        }catch (Exception e){
            fieldName= error.getObjectName();
        }
        String message= error.getDefaultMessage();
        errorMessages.add(String.format("%S :%S",fieldName,message));
    }
}