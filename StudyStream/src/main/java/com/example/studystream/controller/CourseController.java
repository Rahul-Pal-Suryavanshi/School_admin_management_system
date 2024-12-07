package com.example.studystream.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studystream.dto.CourseDto;
import com.example.studystream.entities.Course;
import com.example.studystream.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/courses")
@Tag(name = "Course Management", description = "Operations related to courses")
public class CourseController {
	
	@Autowired
	CourseService courseService;
	
	@PostMapping
	@Operation(summary = "Create a new course", description = "Adds a new course to the system")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Course created successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid course data")
	})
	public ResponseEntity<Course> save(@Valid @RequestBody Course course){
		return new ResponseEntity<Course>(courseService.save(course),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get course by ID", description = "Retrieves a specific course by its ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Course found"),
	    @ApiResponse(responseCode = "500", description = "Course not found")
	})
	public ResponseEntity<CourseDto> findByCourseId(@PathVariable ("id") long id){
		return new ResponseEntity<CourseDto>(courseService.findById(id),HttpStatus.OK);
	}
	
	@GetMapping
	@Operation(summary = "Get all courses", description = "Retrieves a list of all courses")
	@ApiResponse(responseCode = "200", description = "Successful retrieval of course list")
	public ResponseEntity<List<Course>> getAllCourses(){
		return new ResponseEntity<List<Course>>(courseService.findAll(),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{courseid}")
	@Operation(summary = "Delete a course", description = "Removes a course from the system")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Course deleted successfully"),
	    @ApiResponse(responseCode = "500", description = "Course not found")
	})
	public ResponseEntity<String> deleteCourseById(@PathVariable("courseid") long id){
		courseService.delete(id);
		return new ResponseEntity<String>("Course deleted successfully",HttpStatus.OK);
	}
	
}
