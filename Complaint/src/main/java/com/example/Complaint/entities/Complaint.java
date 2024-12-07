package com.example.Complaint.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "complaints")
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Student ID cannot be null")
	private Long studentId; // Store the ID of the student who raised the complaint

	@NotBlank(message = "Subject cannot be blank")
	private String subject;

	@NotBlank(message = "Description cannot be blank")
	@Size(max = 255, message = "Description cannot exceed 255 characters")
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
		OPEN, IN_PROGRESS, RESOLVED, CLOSED
	}
}
