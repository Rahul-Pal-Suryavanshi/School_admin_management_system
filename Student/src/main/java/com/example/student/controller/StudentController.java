package com.example.student.controller;

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

import com.example.student.dto.ComplaintDetailsDto;
import com.example.student.dto.ComplaintDto;
import com.example.student.dto.CourseDto;
import com.example.student.dto.HomeworkDto;
import com.example.student.dto.NoticeDto;
import com.example.student.dto.StudyMaterialDto;
import com.example.student.entities.Student;
import com.example.student.service.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
@Tag(name = "Student Management", description = "Operations pertaining to student management")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping
	@Operation(summary = "Create a new student", description = "Adds a new student to the system")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Student created successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid input data")
	})
	public ResponseEntity<Student> save(@Valid @RequestBody Student student){
		return new ResponseEntity<Student>(studentService.save(student),HttpStatus.CREATED);
	}

	@GetMapping
	@Operation(summary = "Get all students", description = "Retrieves a list of all students")
	@ApiResponse(responseCode = "200", description = "Successful retrieval of student list")
	public ResponseEntity<List<Student>> findAll(){
		return new ResponseEntity<List<Student>>(studentService.findAll(),HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get a student by ID", description = "Retrieves a specific student by their ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Student found"),
	    @ApiResponse(responseCode = "500", description = "Student not found")
	})
	public ResponseEntity<Student> findStudentById(@PathVariable("id") long id){

		return new ResponseEntity<Student>(studentService.findById(id),HttpStatus.OK);

	}

	@PutMapping("/update/{id}")
	@Operation(summary = "Update a student", description = "Updates the details of an existing student")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Student updated successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid input data")
	})
	public ResponseEntity<Student> updateStudentById(@PathVariable("id") long id ,@Valid @RequestBody Student student){

		return new ResponseEntity<Student>(studentService.updateTeacherById(id,student),HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete a student", description = "Removes a student from the system")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Student deleted successfully"),
	    @ApiResponse(responseCode = "500", description = "Student not found")
	})
	public ResponseEntity<String> deleteStudentById(@PathVariable("id") long id){

		studentService.deleteById(id);

		return new ResponseEntity<String>("Student Deleted Successfully",HttpStatus.OK);

	}

	@PostMapping("/raisecomplaint/{studentid}")
	@Operation(summary = "Raise a complaint", description = "Allows a student to raise a complaint")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Complaint raised successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid complaint details")
	})
	public ResponseEntity<ComplaintDto> raiseComplaint(@Valid @RequestBody ComplaintDetailsDto complaintDetailsDto,@PathVariable("studentid")long id){

		ComplaintDto complaintDto = studentService.raiseComplaint(complaintDetailsDto, id);

		return new ResponseEntity<ComplaintDto>(complaintDto,HttpStatus.CREATED);

	}
	
	@GetMapping("/getstudentbystandard/{standard}")
	@Operation(summary = "Get students by standard", description = "Retrieves students based on their standard")
	@ApiResponse(responseCode = "200", description = "Successful retrieval of students")
	public ResponseEntity<List<Student>> findByStandard(@PathVariable("standard") String standard){
		
		List<Student> ls = studentService.findByStandard(standard);
		
		return new ResponseEntity<List<Student>>(ls, HttpStatus.OK);
	}
	
	@GetMapping("courses/{courseid}/materials/{standard}")
	@Operation(summary = "Get course materials by course ID and standard", description = "Retrieves course materials for a specific course and standard")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Course materials retrieved successfully"),
	    @ApiResponse(responseCode = "500", description = "Course or materials not found")
	})
    public ResponseEntity<Set<StudyMaterialDto>> getCourseWithMaterials(@PathVariable("courseid") long courseId,@PathVariable("standard")String standard) {
        return new ResponseEntity<Set<StudyMaterialDto>>(studentService.getCourseWithMaterialsOfSpecificStandard(courseId,standard),HttpStatus.OK);
    }
 
	@GetMapping("/viewallnotices")
	@Operation(summary = "View all notices", description = "Retrieves all notices available")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful retrieval of notices"),
			@ApiResponse(responseCode = "500", description = "No Notices found")
	})
	public ResponseEntity<List<NoticeDto>> getAllNotices(){
		return new ResponseEntity<List<NoticeDto>>(studentService.getAllNotices(),HttpStatus.OK);
	}
	
	@GetMapping("/findhomework/{standard}")
	@Operation(summary = "Find homework by standard", description = "Retrieves homework based on standard")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful retrieval of homework"),
			@ApiResponse(responseCode = "500", description = "Homeworks not found")
	})
	public ResponseEntity<List<HomeworkDto>> findHomeworkByStandard(@PathVariable("standard")String standard){
		return new ResponseEntity<List<HomeworkDto>>(studentService.getHomeworkByStandard(standard),HttpStatus.OK);
	}

}
