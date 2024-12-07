package com.example.Teacher.dto;

import java.util.Set;

public class CourseDto {
	
	private Long id;
	private String coursename;
	private long teacherId;
	private Set<StudyMaterialDto> studyMaterialDtos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}
	public Set<StudyMaterialDto> getStudyMaterialDtos() {
		return studyMaterialDtos;
	}
	public void setStudyMaterialDtos(Set<StudyMaterialDto> studyMaterialDtos) {
		this.studyMaterialDtos = studyMaterialDtos;
	}
	
	
}
