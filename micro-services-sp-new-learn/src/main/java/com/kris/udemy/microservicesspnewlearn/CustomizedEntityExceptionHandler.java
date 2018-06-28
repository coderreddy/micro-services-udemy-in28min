package com.kris.udemy.microservicesspnewlearn;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception 
	{
		ResponseException resException = new ResponseException(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(resException,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(ResourceNotFoundException ex, WebRequest request) throws Exception 
	{
		ResponseException resException = new ResponseException(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(resException,HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ResponseException resException = new ResponseException(new Date(),"Provided parameter is not valid",ex.getBindingResult().toString());
		return new ResponseEntity(resException,HttpStatus.BAD_REQUEST);
	}


}
