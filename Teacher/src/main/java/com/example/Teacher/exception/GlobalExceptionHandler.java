package com.example.Teacher.exception;

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
	
	@ExceptionHandler(value = TeacherNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleTeacherNotFoundException (TeacherNotFoundException ex, WebRequest request)
	{
		ErrorDetails details = new ErrorDetails();
		details.setMessage(ex.getMessage());
		details.setDate(new Date());
		details.setPath(request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = HomeworkNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleHomeworkNotFoundException (HomeworkNotFoundException ex, WebRequest request)
	{
		ErrorDetails details = new ErrorDetails();
		details.setMessage(ex.getMessage());
		details.setDate(new Date());
		details.setPath(request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = StudyMaterialsNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleStudyMaterialsNotFoundException (StudyMaterialsNotFoundException ex, WebRequest request)
	{
		ErrorDetails details = new ErrorDetails();
		details.setMessage(ex.getMessage());
		details.setDate(new Date());
		details.setPath(request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = StudentsNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleStudentsNotFoundException (StudentsNotFoundException ex, WebRequest request)
	{
		ErrorDetails details = new ErrorDetails();
		details.setMessage(ex.getMessage());
		details.setDate(new Date());
		details.setPath(request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = NoticesNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleNoticesNotFoundException (NoticesNotFoundException ex, WebRequest request)
	{
		ErrorDetails details = new ErrorDetails();
		details.setMessage(ex.getMessage());
		details.setDate(new Date());
		details.setPath(request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<Object>
	handleMethodArgumentNotValidException (MethodArgumentNotValidException ex , WebRequest request)
	{
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((e)->
		{
			String fieldName = ((FieldError)e).getField();
			String message = e.getDefaultMessage();
			errors.put(fieldName, message);
			});
		return new ResponseEntity<Object>(errors,HttpStatus.INTERNAL_SERVER_ERROR);
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
