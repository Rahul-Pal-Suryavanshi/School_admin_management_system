package com.example.student.dto;

import java.time.LocalDate;



public class HomeworkDto {
	
	private Long id;
    private String title;
    private String description;
    private String toWhichStandard;
    private LocalDate dueDate;
    
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getToWhichStandard() {
		return toWhichStandard;
	}
	public void setToWhichStandard(String toWhichStandard) {
		this.toWhichStandard = toWhichStandard;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
    
}
