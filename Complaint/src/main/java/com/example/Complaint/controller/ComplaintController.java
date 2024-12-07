package com.example.Complaint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Complaint.dto.ComplaintDetails;
import com.example.Complaint.entities.Complaint;
import com.example.Complaint.service.ComplaintService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/complaints")
@Tag(name = "Complaint Management", description = "Operations related to complaints")
public class ComplaintController {
	
	@Autowired
	ComplaintService complaintService;
	
	@PostMapping("/raise/{id}")
	@Operation(summary = "Raise a new complaint", description = "Creates a new complaint for a specific student")
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "201", description = "Complaint raised successfully"),
	    @ApiResponse(responseCode = "500", description = "Invalid complaint data")
	})
    public Complaint raiseComplaint(@Valid @RequestBody ComplaintDetails complaintDetails,@PathVariable("id") long studentId) {
        return complaintService.raiseComplaint(studentId, complaintDetails);
    }

    @GetMapping
    @Operation(summary = "Get all complaints", description = "Retrieves a list of all complaints")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of complaints list")
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @PutMapping("update-complaint-status/{id}")
    @Operation(summary = "Update complaint status", description = "Changes the status of a specific complaint")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Complaint status updated successfully"),
        @ApiResponse(responseCode = "500", description = "Complaint not found")
    })
    public ResponseEntity<String> changeComplaintStatus(@PathVariable Long id, @RequestBody Complaint.ComplaintStatus status) {
        complaintService.changeComplaintStatus(id, status);
        return new ResponseEntity<String>("Complaint Status updated to "+status,HttpStatus.OK);
    }
}
