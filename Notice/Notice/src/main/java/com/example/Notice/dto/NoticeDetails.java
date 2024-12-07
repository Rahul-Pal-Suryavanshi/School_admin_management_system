package com.example.Notice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NoticeDetails {
	
	@NotBlank(message = "Title must not be blank")
	private String title;
	
	@NotBlank(message = "Content must not be blank")
	@Size(max = 255, message = "Content must not exceed 255 characters")
    private String content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public NoticeDetails(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	public NoticeDetails() {
		super();
		// TODO Auto-generated constructor stub
	} 
    
}
