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

import com.example.studystream.dao.CourseRepository;
import com.example.studystream.entities.Course;
import com.example.studystream.entities.StudyMaterial;
import com.example.studystream.service.CourseService;
import com.example.studystream.service.StudyMaterialService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/study-materials")
@Tag(name = "Study Material Management", description = "Operations related to study materials")
public class StudyMaterialController {
	
	@Autowired
	StudyMaterialService studyMaterialService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	CourseRepository courseRepository;
	
	@PostMapping
	@Operation(summary = "Create a new study material", description = "Adds a new study material to the system")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Study material created successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid study material data")
	})
	public ResponseEntity<StudyMaterial> save(@Valid @RequestBody StudyMaterial studyMaterial){
		return new ResponseEntity<StudyMaterial>(studyMaterialService.save(studyMaterial),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Get study material by ID", description = "Retrieves a specific study material by its ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Study material found"),
	    @ApiResponse(responseCode = "500", description = "Study material not found")
	})
	public ResponseEntity<StudyMaterial> findByStudyMaterialById(@PathVariable ("id") long id){
		return new ResponseEntity<StudyMaterial>(studyMaterialService.findById(id),HttpStatus.OK);
	}
	
	@GetMapping
	@Operation(summary = "Get all study materials", description = "Retrieves a list of all study materials")
	@ApiResponse(responseCode = "200", description = "Successful retrieval of study materials list")
	public ResponseEntity<List<StudyMaterial>> getAllCourses(){
		return new ResponseEntity<List<StudyMaterial>>(studyMaterialService.findAll(),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{studymaterialid}")
	@Operation(summary = "Delete study material", description = "Removes a study material from the system")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Study material deleted successfully"),
	    @ApiResponse(responseCode = "500", description = "Study material not found")
	})
	public ResponseEntity<String> deleteStudyMaterialById(@PathVariable("courseid") long id){
		studyMaterialService.delete(id);
		return new ResponseEntity<String>("Course deleted successfully",HttpStatus.OK);
	}
	
	@PostMapping("/add-studymaterials/courses/{courseId}")
	@Operation(summary = "Add study material to a course", description = "Associates a new study material with a specific course")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Study material added to course successfully"),
	    @ApiResponse(responseCode = "500", description = "Course not found")
	})
	public ResponseEntity<StudyMaterial> addStudyMaterial(@PathVariable("courseId") Long courseId,@Valid @RequestBody StudyMaterial studyMaterial) {
	    Course course = courseRepository.findById(courseId).orElseThrow();
	    studyMaterial.setCourse(course);
	    course.getStudyMaterials().add(studyMaterial);
	    courseService.save(course); // Save the course which cascades to study materials
	    return  new ResponseEntity<StudyMaterial>(studyMaterial,HttpStatus.OK);
	}

}
