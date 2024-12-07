package com.example.Complaint.service;

import java.util.List;

import com.example.Complaint.dto.ComplaintDetails;
import com.example.Complaint.entities.Complaint;

public interface ComplaintService {
	
	Complaint raiseComplaint(Long studentId,ComplaintDetails complaintDetails);
	List<Complaint> getAllComplaints();
	void changeComplaintStatus(Long complaintId, Complaint.ComplaintStatus status);
}
