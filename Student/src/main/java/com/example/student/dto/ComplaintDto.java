package com.example.student.dto;

import java.time.LocalDateTime;

public class ComplaintDto {
	private Long id;
	 
    private Long studentId; // Store the ID of the student who raised the complaint
 
    private String subject;
    private String description;
    private LocalDateTime raisedAt;
    private ComplaintStatus status;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getRaisedAt() {
		return raisedAt;
	}
	public void setRaisedAt(LocalDateTime raisedAt) {
		this.raisedAt = raisedAt;
	}
	public ComplaintStatus getStatus() {
		return status;
	}
	public void setStatus(ComplaintStatus status) {
		this.status = status;
	}
 
	public enum ComplaintStatus {
        OPEN,
        IN_PROGRESS,
        RESOLVED,
        CLOSED
    }

}
