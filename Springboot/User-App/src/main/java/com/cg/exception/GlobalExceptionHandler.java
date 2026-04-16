package com.cg.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorDetails handleUserNotFoundException(UserNotFoundException exception, WebRequest request) {

		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimestamp(LocalDateTime.now());
		errorDetails.setErrorMessage(exception.getMessage());
		errorDetails.setPath(request.getDescription(false));
		errorDetails.setErrorCode(String.valueOf(HttpStatus.NOT_FOUND));

		return errorDetails;
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDetails handleUserExstsException(UserAlreadyExistsException exception, WebRequest request) {

		return new ErrorDetails(LocalDateTime.now(), exception.getMessage(), request.getDescription(false),
				String.valueOf(HttpStatus.BAD_REQUEST));

	}

}
