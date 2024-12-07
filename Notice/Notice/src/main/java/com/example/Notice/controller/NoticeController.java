package com.example.Notice.controller;

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

import com.example.Notice.dto.NoticeDetails;
import com.example.Notice.entities.Notice;
import com.example.Notice.service.NoticeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/notices")
@Tag(name = "Notice Management", description = "Operations related to notices")
public class NoticeController {
	
	@Autowired
	NoticeService noticeService;
	
	@PostMapping("/post")
	@Operation(summary = "Create a new notice", description = "Adds a new notice to the system")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Notice created successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid notice data")
	})
	public ResponseEntity<Notice> save(@Valid @RequestBody NoticeDetails noticeDetails){
		return new ResponseEntity<Notice>(noticeService.save(noticeDetails),HttpStatus.CREATED);
	}
	
	@GetMapping("/get-all-notices")
	@Operation(summary = "Get all notices", description = "Retrieves a list of all notices")
	@ApiResponse(responseCode = "200", description = "Successful retrieval of notices list")
	public ResponseEntity<List<Notice>> findAll(){
		return new ResponseEntity<List<Notice>>(noticeService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/byid/{id}")
	@Operation(summary = "Get notice by ID", description = "Retrieves a specific notice by its ID")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Notice found"),
	    @ApiResponse(responseCode = "500", description = "Notice not found")
	})
	public ResponseEntity<Notice> findById(@PathVariable("id") Long id){
		return new ResponseEntity<Notice>(noticeService.findById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete a notice", description = "Removes a notice from the system")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = "Notice deleted successfully"),
	    @ApiResponse(responseCode = "500", description = "Notice not found")
	})
	public ResponseEntity<String> delete(@PathVariable("id")Long id){
		noticeService.deleteById(id);
		return new ResponseEntity<String>("Notice deleted successfully", HttpStatus.OK);
	}
	
	//To perform CRUD operations
	//http://localhost:8100/swagger-ui/index.html
	
}
