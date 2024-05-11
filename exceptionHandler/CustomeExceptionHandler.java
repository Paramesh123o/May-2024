package com.harish.demo.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.harish.demo.exception.BikeNotFoundException;
import com.harish.demo.model.ErrorResponse;

@RestControllerAdvice
public class CustomeExceptionHandler {
	@ExceptionHandler(BikeNotFoundException.class)
public ResponseEntity<ErrorResponse> handleBikeNotFoundException(BikeNotFoundException e)
{

		ErrorResponse errorResponse=new ErrorResponse(e.getMessage(),
				HttpStatus.BAD_REQUEST,
				"Product Not found",
				e.getClass().toString()
				);
		ResponseEntity<ErrorResponse> errorMessage= new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
		return errorMessage;
}
public ResponseEntity<ErrorResponse> handleException(Exception e)
{
	ErrorResponse errorResponse=new ErrorResponse(e.getMessage(),
			HttpStatus.BAD_REQUEST,
			"Product Not found",
			e.getClass().toString()
			);
	ResponseEntity<ErrorResponse> errorMessage= new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
	return errorMessage;
}
}
