package com.example.student.dto;

import java.time.LocalDateTime;

public class NoticeDto {
	
	private Long id;
    private String title;
    private String content; 
    private LocalDateTime datePosted;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public LocalDateTime getDatePosted() {
		return datePosted;
	}
	public void setDatePosted(LocalDateTime datePosted) {
		this.datePosted = datePosted;
	}
    
}
