package com.example.studystream.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studystream.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{
	
	Course findByCoursename(String coursename);
}
