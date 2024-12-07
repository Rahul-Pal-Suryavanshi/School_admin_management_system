package com.example.Teacher.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "teachers")
public class Teacher {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Name cannot be null or empty")
	private String name;
	
	@Email(message = "Email should be valid")
	private String email;
	
	@Pattern(regexp = "^(6|7|8|9|10|11|12)$", message = "Class teacher of must be between 6 and 12")
	private String classTeacherOf;
	
	@NotNull(message = "Qualification cannot be null")
	private String qualification;
	
	
	private String address;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClassTeacherOf() {
		return classTeacherOf;
	}
	public void setClassTeacherOf(String classTeacherOf) {
		this.classTeacherOf = classTeacherOf;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Teacher(long id, String name, String email, String classTeacherOf, String qualification, String address) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.classTeacherOf = classTeacherOf;
		this.qualification = qualification;
		this.address = address;
	}

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
