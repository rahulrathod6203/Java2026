package com.cg.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CustomerNotFoundException.class)
	public ErrorDetails handler(CustomerNotFoundException exception, WebRequest request) {
		return new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false),
				String.valueOf(HttpStatus.NOT_FOUND));
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CustomerExistsException.class)
	public ErrorDetails emailExistsHandler(CustomerExistsException exception, WebRequest request) {
		return new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false),
				String.valueOf(HttpStatus.BAD_REQUEST));
	}

}
