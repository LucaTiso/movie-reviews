package com.luca.moviereviews.webapp.controllers;

import java.util.HashSet;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.luca.moviereviews.core.model.BusinessErrorCodes;
import com.luca.moviereviews.responses.ExceptionResponse;

import jakarta.mail.MessagingException;

@RestControllerAdvice
public class MovieReviewsExceptionHandler {
	
	/*
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<?> handleValidationError(MethodArgumentNotValidException  ex){
		 Map<String, String> errors = new HashMap<>();
		    ex.getBindingResult().getAllErrors().forEach((error) -> {
		        String fieldName = ((FieldError) error).getField();
		        String errorMessage = error.getDefaultMessage();
		        errors.put(fieldName, errorMessage);
		    });
		    return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		    
	}*/
	
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<?> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex){
		
		
		String message="metodo http "+ex.getMethod()+" non supportato per questo endpoint";
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(LockedException.class)
	public ResponseEntity<ExceptionResponse> handleException(LockedException exp){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
				ExceptionResponse.builder().businessErrorCode(BusinessErrorCodes.ACCOUNT_LOCKED.getCode())
				.businessErrorDescription(BusinessErrorCodes.ACCOUNT_LOCKED.getDescription())
				.error(exp.getMessage()).build()
				);
		
	}
	
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException exp){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
				ExceptionResponse.builder().businessErrorCode(BusinessErrorCodes.BAD_CREDENTIALS.getCode())
				.businessErrorDescription(BusinessErrorCodes.BAD_CREDENTIALS.getDescription())
				.error(BusinessErrorCodes.BAD_CREDENTIALS.getDescription()).build()
				);
		
	}
	
	@ExceptionHandler(MessagingException.class)
	public ResponseEntity<ExceptionResponse> handleException(MessagingException exp){
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
				ExceptionResponse.builder()
				.error(exp.getMessage()).build()
				);
		
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exp){
		
		Set<String> errors =new HashSet<>();
		
		exp.getBindingResult().getAllErrors().forEach(error->{
			var errorMessage=error.getDefaultMessage();
			errors.add(errorMessage);
		});
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				ExceptionResponse.builder()
				.validationErrors(errors).build());
			
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> handleException(Exception exp){
		
		exp.printStackTrace();
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse.builder().businessErrorDescription("Internal error, contact the admin")
				.error(exp.getMessage()).build());
			
		
	}

}
