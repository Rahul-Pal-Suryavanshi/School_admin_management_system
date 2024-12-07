package com.example.studystream.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studystream.dao.CourseRepository;
import com.example.studystream.dto.CourseDto;
import com.example.studystream.entities.Course;
import com.example.studystream.exception.CoursesNotFoundException;
import com.example.studystream.mapper.StudyStreamMapper;
import com.example.studystream.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	CourseRepository courseRepository;
	
	
	@Override
	public Course save(Course course) {
		// TODO Auto-generated method stub
		return courseRepository.save(course);
	}


	@Override
	public List<Course> findAll() {
		List<Course> courses = courseRepository.findAll();
		
		if (courses == null || courses.isEmpty()) {
			throw new CoursesNotFoundException("No courses found.");
		}

		return courses; 
	}
	

	@Override
	public void delete(long id) {
		Course course = courseRepository.findById(id).orElseThrow(() -> new CoursesNotFoundException("Course not found with id: " + id));
		courseRepository.delete(course);
	}


	@Override
	public CourseDto findById(long id) {
		Course course = courseRepository.findById(id).orElseThrow(() -> new CoursesNotFoundException("Course not found with id: " + id));
		return StudyStreamMapper.courseToCourseDto(course);
	}


}
