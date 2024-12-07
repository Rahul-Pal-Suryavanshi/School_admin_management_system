package com.example.studystream.exception;


public class CoursesNotFoundException extends RuntimeException{
	public CoursesNotFoundException(String msg) {
		super(msg);
	}
}
