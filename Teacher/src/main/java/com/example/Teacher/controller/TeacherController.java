package com.example.Teacher.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Teacher.dto.NoticeDetailsDto;
import com.example.Teacher.dto.NoticeDto;
import com.example.Teacher.dto.StudentDto;
import com.example.Teacher.dto.StudyMaterial;
import com.example.Teacher.dto.StudyMaterialDto;
import com.example.Teacher.entities.Homework;
import com.example.Teacher.entities.Teacher;
import com.example.Teacher.service.HomeworkService;
import com.example.Teacher.service.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/teachers")
@Tag(name = "Teacher Management", description = "Operations related to teachers")
public class TeacherController {
	
	@Autowired
	TeacherService teacherService;
	
	@Autowired
	HomeworkService homeworkService;
	
	@PostMapping
	@Operation(summary = "Create a new teacher", description = "Adds a new teacher to the system")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Teacher created successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid input data")
	})
	public ResponseEntity<Teacher> save(@Valid @RequestBody Teacher teacher){
		return new ResponseEntity<Teacher>(teacherService.save(teacher),HttpStatus.CREATED);
	}
	
	@GetMapping
	@Operation(summary = "Get all teachers", description = "Retrieves a list of all teachers")
	@ApiResponse(responseCode = "200", description = "Successful retrieval of teacher list")
	public ResponseEntity<List<Teacher>> findAll(){
		return new ResponseEntity<List<Teacher>>(teacherService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/getteacherbyid/{id}")
	@Operation(summary = "Get a teacher by ID", description = "Retrieves a specific teacher by their ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Teacher found"),
	    @ApiResponse(responseCode = "500", description = "Teacher not found")
	})
	public ResponseEntity<Teacher> findTeacherById(@PathVariable("id") long id){
		return new ResponseEntity<Teacher>(teacherService.findById(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateteacher/{id}")
	@Operation(summary = "Update a teacher", description = "Updates the details of an existing teacher")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Teacher updated successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid input data")
	})
	public ResponseEntity<Teacher> updateTeacherById(@PathVariable("id") long id ,@Valid @RequestBody Teacher teacher){
		return new ResponseEntity<Teacher>(teacherService.updateTeacherById(id,teacher),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteteacher/{id}")
	@Operation(summary = "Delete a teacher", description = "Removes a teacher from the system")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Teacher deleted successfully"),
	    @ApiResponse(responseCode = "500", description = "Teacher not found")
	})
	public ResponseEntity<String> deleteTeacherById(@PathVariable("id") long id){
		teacherService.deleteById(id);
		return new ResponseEntity<String>("Teacher Deleted Successfully",HttpStatus.OK);
	}
	
	
	@GetMapping("/studentbystandard/{standard}")
	@Operation(summary = "Get students by standard", description = "Retrieves students based on their standard")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful retrieval of students"),
			@ApiResponse(responseCode = "500", description = "Students not found")
	})
	public ResponseEntity<List<StudentDto>> getStududentByStandard(@PathVariable("standard") String standard){
		return new ResponseEntity<List<StudentDto>>(teacherService.findByStandard(standard),HttpStatus.OK);
	}
	
	@GetMapping("/viewallmaterials/{courseid}")
	@Operation(summary = "View all study materials", description = "Retrieves all study materials for a specific course")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Successful retrieval of study materials"),
	    @ApiResponse(responseCode = "500", description = "Study materials not found")
	})
	public ResponseEntity<Set<StudyMaterialDto>> viewAllStudyMaterials(@PathVariable("courseid")long id){
		return new ResponseEntity<Set<StudyMaterialDto>>(teacherService.viewStudyMaterials(id),HttpStatus.OK);
	}
	
	@PostMapping("/addstudymaterial/{courseid}")
	@Operation(summary = "Add study material", description = "Adds new study material for a specific course")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Study material added successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid study material data")
	})
    public ResponseEntity<String> addStudyMaterial(@PathVariable("courseid") Long id,@Valid @RequestBody StudyMaterial studyMaterial) {
        teacherService.addStudyMaterial(id, studyMaterial);
        return new ResponseEntity<String>("studymaterial added successfully in the course "+id,HttpStatus.OK);
    }
	
	@GetMapping("/checknotices")
	@Operation(summary = "View all notices", description = "Retrieves all notices available")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful retrieval of notices"),
			@ApiResponse(responseCode = "500", description = "No Notices found")
	})
	public ResponseEntity<List<NoticeDto>> viewAllNotices(){
		return new ResponseEntity<List<NoticeDto>>(teacherService.viewAllNotices(),HttpStatus.OK);
	}
	
	@PostMapping("/postnotice")
	@Operation(summary = "Post a notice", description = "Allows a teacher to post a notice")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Notice posted successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid notice details")
	})
	public ResponseEntity<NoticeDto> addNotice(@Valid @RequestBody NoticeDetailsDto noticeDetailsDto){
		return new ResponseEntity<NoticeDto>(teacherService.postNotice(noticeDetailsDto),HttpStatus.CREATED);
	}
	
	@PostMapping("/addhomework")
	@Operation(summary = "Add homework", description = "Allows a teacher to add homework")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Homework added successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid homework data")
	})
	public ResponseEntity<Homework> save(@Valid @RequestBody Homework homework){
		return new ResponseEntity<Homework>(homeworkService.save(homework),HttpStatus.CREATED);
	}
	
}
