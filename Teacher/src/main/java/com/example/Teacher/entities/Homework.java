package com.example.Teacher.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "homeworks")
public class Homework {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank(message = "Title cannot be blank")
    private String title;
    
	@NotBlank(message = "Description cannot be blank")
    private String description;
    
	@NotBlank(message = "Standard cannot be blank")
    @Pattern(regexp = "^(6|7|8|9|10|11|12)$", message = "Standard must be between 6 and 12")
    @Column(name = "to_which_standard")
    private String toWhichStandard;
    
	@NotNull(message = "Due date cannot be null")
    @Future(message = "Due date must be in the future")
    private LocalDateTime dueDate;

	public Long getId() {
		return id;
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

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Homework(String title, String description, String toWhichStandard, LocalDateTime dueDate) {
		super();
		this.title = title;
		this.description = description;
		this.toWhichStandard = toWhichStandard;
		this.dueDate = dueDate;
	}

	public Homework() {
		super();
		// TODO Auto-generated constructor stub
	}

}
