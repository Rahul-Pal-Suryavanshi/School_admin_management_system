package com.example.Complaint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ComplaintDetails {
	
	@NotBlank(message = "Subject cannot be blank")
	private String subject;
	
	@NotBlank(message = "Description cannot be blank")
	@Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    public ComplaintDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComplaintDetails(String subject, String description) {
		super();
		this.subject = subject;
		this.description = description;
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
}
