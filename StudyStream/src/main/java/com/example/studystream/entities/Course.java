package com.example.studystream.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Course name is mandatory")
	private String coursename;
	
	@NotNull(message = "Teacher ID is mandatory")
	@Column(name = "teacher_id")
	private Long teacherId;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "course")
	@JsonManagedReference
	Set<StudyMaterial> studyMaterials;
	
	public Set<StudyMaterial> getStudyMaterials() {
		return studyMaterials;
	}
	public void setStudyMaterials(Set<StudyMaterial> studyMaterials) {
		this.studyMaterials = studyMaterials;
	}
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
	public Course(Long id, String coursename, long teacherId, Set<StudyMaterial> studyMaterials) {
		super();
		this.id = id;
		this.coursename = coursename;
		this.teacherId = teacherId;
		this.studyMaterials = studyMaterials;
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
