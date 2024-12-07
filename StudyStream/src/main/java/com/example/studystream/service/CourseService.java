package com.example.studystream.service;

import java.util.List;

import com.example.studystream.dto.CourseDto;
import com.example.studystream.entities.Course;

public interface CourseService {
	
	Course save(Course course);
	CourseDto findById(long id);
	List<Course> findAll();
	void delete(long id);

}
