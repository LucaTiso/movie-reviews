package com.luca.moviereviews.core.model;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import lombok.Getter;

public enum BusinessErrorCodes {
	
	
	NO_CODE(0,NOT_IMPLEMENTED,"no code"),
	ACCOUNT_LOCKED(302,FORBIDDEN,"User account is locked"),
	NEW_PASSWORD_DOES_NOT_MATCH(301,BAD_REQUEST,"New password does not match"),
	ACCOUNT_DISABLED(303,FORBIDDEN	,"User account is disabled"),
	BAD_CREDENTIALS(304,FORBIDDEN	,"Login and / or password is incorrect"),
	INCORRECT_CURRENT_PASSWORD(300,BAD_REQUEST,"Current password is incorrect");
	
	@Getter
	private final int code;
	@Getter
	private final String description;
	@Getter
	private final HttpStatus httpStatus;
	

	 BusinessErrorCodes(int code,HttpStatus httpStatus, String description ) {
		this.code = code;
		this.description = description;
		this.httpStatus = httpStatus;
	}

}
