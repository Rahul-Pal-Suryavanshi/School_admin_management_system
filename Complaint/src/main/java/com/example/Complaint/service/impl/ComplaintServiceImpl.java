package com.example.Complaint.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Complaint.dao.ComplaintRepository;
import com.example.Complaint.dto.ComplaintDetails;
import com.example.Complaint.entities.Complaint;
import com.example.Complaint.entities.Complaint.ComplaintStatus;
import com.example.Complaint.exception.ComplaintNotFoundException;
import com.example.Complaint.service.ComplaintService;

@Service
public class ComplaintServiceImpl implements ComplaintService{
	
	@Autowired
	ComplaintRepository complaintRepository;

	@Override
	public Complaint raiseComplaint(Long studentId, ComplaintDetails complaintDetails) {
		
		Complaint complaint = new Complaint();
		complaint.setSubject(complaintDetails.getSubject());
		complaint.setDescription(complaintDetails.getDescription());
		complaint.setStudentId(studentId);
        complaint.setRaisedAt(LocalDateTime.now());
        complaint.setStatus(Complaint.ComplaintStatus.OPEN); // Default status

        return complaintRepository.save(complaint);
	}

	@Override
	public List<Complaint> getAllComplaints() {
		return complaintRepository.findAll();
	}

	@Override
	public void changeComplaintStatus(Long complaintId, ComplaintStatus status) {
		Complaint complaint = complaintRepository.findById(complaintId).orElseThrow(()->new ComplaintNotFoundException("Complaint not found with id: "+complaintId));
		complaint.setStatus(status);
		complaintRepository.save(complaint);
	}

	

}
