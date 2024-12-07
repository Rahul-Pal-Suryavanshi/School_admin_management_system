package com.example.student.exception;

import java.util.Date;

public class ErrorDetails {
	private String message;
	private Date date;
	private String path;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "ErrorDetails [message=" + message + ", date=" + date + ", path=" + path + "]";
	}
}
