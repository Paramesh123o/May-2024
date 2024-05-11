package com.harish.demo.model;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
private String message;
private HttpStatus code;
private String error;
private String classType;
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public HttpStatus getCode() {
	return code;
}
public void setCode(HttpStatus code) {
	this.code = code;
}
public String getError() {
	return error;
}
public void setError(String error) {
	this.error = error;
}
public String getClassType() {
	return classType;
}
public void setClassType(String classType) {
	this.classType = classType;
}
/**
 * 
 */
public ErrorResponse() {
	super();
	// TODO Auto-generated constructor stub
}
public ErrorResponse(String message, HttpStatus badRequest, String error, String classType) {
	super();
	this.message = message;
	this.code = badRequest;
	this.error = error;
	this.classType = classType;
}


}
