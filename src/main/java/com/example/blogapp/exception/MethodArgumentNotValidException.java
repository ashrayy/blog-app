//package com.example.easynotes.exception;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;
//
//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
//@Getter
//@Setter
//public class MethodArgumentNotValidException extends RuntimeException{
//    private String resourceName;
//    private String fieldName;
//    private Object fieldValue;
//
//    MethodArgumentNotValidException(String resourceName,String fieldName,Object fieldValue){
//        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
//        this.resourceName = resourceName;
//        this.fieldName = fieldName;
//        this.fieldValue = fieldValue;
//    }
//
//
//}
