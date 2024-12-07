package com.example.student.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Name cannot be null or empty")
	private String name;
	
	@Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
	private String phnNumber;
	
	private String address;
	
	@Pattern(regexp = "^(6|7|8|9|10|11|12)$", message = "Standard must be between 6 and 12")
	private String standard;
	
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhnNumber() {
		return phnNumber;
	}
	public void setPhnNumber(String phnNumber) {
		this.phnNumber = phnNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}

}
