package com.example.Teacher.dto;

public class StudentDto {
	private Long id;
	private String name;
	private String phnNumber;
	private String address;
	private String standard;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public StudentDto(Long id, String name, String phnNumber, String address, String standard) {
		super();
		this.id = id;
		this.name = name;
		this.phnNumber = phnNumber;
		this.address = address;
		this.standard = standard;
	}
	public StudentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
