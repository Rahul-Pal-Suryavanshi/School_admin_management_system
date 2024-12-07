package com.example.Notice.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "notices")
public class Notice {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Title must not be blank")
    private String title;
	
	@NotBlank(message = "Content must not be blank")
	@Size(max = 255, message = "Content must not exceed 255 characters")
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
	public Notice(Long id, String title, String content, LocalDateTime datePosted) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.datePosted = datePosted;
	}
	public Notice() {
		super();
		// TODO Auto-generated constructor stub
	}
}
