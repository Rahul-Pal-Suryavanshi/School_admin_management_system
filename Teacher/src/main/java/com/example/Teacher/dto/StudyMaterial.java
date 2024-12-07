package com.example.Teacher.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class StudyMaterial {
	
	@NotBlank(message = "StudyMaterial name is mandatory")
	private String materialname;
	
	@Pattern(regexp = "^(6|7|8|9|10|11|12)$", message = "Standard must be between 6 and 12")
	private String standard;
	
	
	public String getMaterialname() {
		return materialname;
	}
	public void setMaterialname(String materialname) {
		this.materialname = materialname;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	
}
