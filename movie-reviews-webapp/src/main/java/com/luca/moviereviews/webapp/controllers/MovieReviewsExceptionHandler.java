package com.luca.moviereviews.webapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MovieReviewsExceptionHandler {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<?> handleValidationError(MethodArgumentNotValidException  ex){
		 Map<String, String> errors = new HashMap<>();
		    ex.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
		    return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		    
	}
	
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<?> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex){
		
		
		String message="metodo http "+ex.getMethod()+" non supportato per questo endpoint";
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		
	}

}
