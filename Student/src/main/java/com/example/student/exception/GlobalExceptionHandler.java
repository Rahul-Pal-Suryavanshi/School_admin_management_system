package com.example.student.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails();
		details.setMessage(exception.getMessage());
		details.setDate(new Date());
		details.setPath(request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
			WebRequest request) {
		
		Map<String, String> errors = new HashMap<>();
		
		exception.getBindingResult().getAllErrors().forEach((e) -> {
			String fieldName = ((FieldError) e).getField();
			String message = e.getDefaultMessage();
			errors.put(fieldName, message);
		});
		
		return new ResponseEntity<Object>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorDetails> handleException(Exception exception, WebRequest request) {
		ErrorDetails details = new ErrorDetails();
		details.setMessage(exception.getMessage());
		details.setDate(new Date());
		details.setPath(request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
