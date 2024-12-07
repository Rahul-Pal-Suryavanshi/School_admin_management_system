package com.example.Teacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Teacher.entities.Homework;
import com.example.Teacher.service.HomeworkService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/homeworks")
@Tag(name = "Homework Management", description = "Operations related to homework")
public class HomeworkController {
	
	@Autowired
	HomeworkService homeworkService;
	
	
	@GetMapping
	@Operation(summary = "Get all homeworks", description = "Retrieves a list of all homework entries")
	@ApiResponse(responseCode = "200", description = "Successful retrieval of homework list")
	public ResponseEntity<List<Homework>> findAll(){
		return new ResponseEntity<List<Homework>>(homeworkService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/gethomeworkById/{id}")
	@Operation(summary = "Get homework by ID", description = "Retrieves a specific homework entry by its ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Homework found"),
	    @ApiResponse(responseCode = "500", description = "Homework not found")
	})
	public ResponseEntity<Homework> findHomeworkById(@PathVariable("id") long id){
		return new ResponseEntity<Homework>(homeworkService.findById(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateHomework/{id}")
	@Operation(summary = "Update homework", description = "Updates an existing homework entry")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Homework updated successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid input data")
	})
	public ResponseEntity<Homework> updateHomeworkById(@PathVariable("id") long id ,@Valid @RequestBody Homework homework){
		return new ResponseEntity<Homework>(homeworkService.updateHomeworkById(id,homework),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteHomework/{id}")
	@Operation(summary = "Delete homework", description = "Removes a homework entry from the system")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Homework deleted successfully"),
	    @ApiResponse(responseCode = "500", description = "Homework not found")
	})
	public ResponseEntity<String> deleteHomeworkById(@PathVariable("id") long id){
		homeworkService.deleteById(id);
		return new ResponseEntity<String>("Homework Deleted Successfully",HttpStatus.OK);
	}
	
	@GetMapping("/getHomeworkByStandard/{standard}")
	@Operation(summary = "Get homework by standard", description = "Retrieves homework entries associated with a specific standard")
	@ApiResponse(responseCode = "200", description = "Successful retrieval of homework by standard")
	public ResponseEntity<List<Homework>> getHomeworkByStandard(@PathVariable("standard") String standard){
		return new ResponseEntity<List<Homework>>(homeworkService.findByToWhichStandard(standard),HttpStatus.OK);
	}

}
