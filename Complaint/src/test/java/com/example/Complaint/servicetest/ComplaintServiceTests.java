package com.example.Complaint.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Complaint.dao.ComplaintRepository;
import com.example.Complaint.dto.ComplaintDetails;
import com.example.Complaint.entities.Complaint;
import com.example.Complaint.exception.ComplaintNotFoundException;
import com.example.Complaint.service.impl.ComplaintServiceImpl;


@ExtendWith(MockitoExtension.class)
public class ComplaintServiceTests {
	@Mock
    private ComplaintRepository repository;

    @InjectMocks
    private ComplaintServiceImpl service;

    private Complaint complaint;

    @BeforeEach
    public void setup() {
        complaint = new Complaint();
        complaint.setId(1L);
        complaint.setStudentId(1L);
        complaint.setSubject("Test Subject");
        complaint.setDescription("This is a test complaint.");
        complaint.setRaisedAt(LocalDateTime.now());
        complaint.setStatus(Complaint.ComplaintStatus.OPEN);
    }

    @DisplayName("JUnit test for raiseComplaint operation")
    @Test
    public void givenComplaintDetails_whenRaiseComplaint_thenReturnComplaintObject() {
        ComplaintDetails complaintDetails = new ComplaintDetails();
        complaintDetails.setSubject("New Complaint");
        complaintDetails.setDescription("Description for new complaint.");

        given(repository.save(any())).willReturn(complaint);

        Complaint raisedComplaint = service.raiseComplaint(1L, complaintDetails);

        assertThat(raisedComplaint).isNotNull();
        assertThat(raisedComplaint.getSubject()).isEqualTo("Test Subject");
    }

    @DisplayName("JUnit test for getAllComplaints operation")
    @Test
    public void whenGetAllComplaints_thenReturnComplaintList() {
        given(repository.findAll()).willReturn(Collections.singletonList(complaint));

        List<Complaint> complaints = service.getAllComplaints();

        assertThat(complaints).isNotEmpty();
        assertThat(complaints.size()).isEqualTo(1);
    }

    @DisplayName("JUnit test for changeComplaintStatus operation")
    @Test
    public void givenComplaintId_whenChangeStatus_thenUpdateComplaintStatus() {
        given(repository.findById(1L)).willReturn(Optional.of(complaint));
        given(repository.save(any())).willReturn(complaint);

        service.changeComplaintStatus(1L, Complaint.ComplaintStatus.RESOLVED);

        assertThat(complaint.getStatus()).isEqualTo(Complaint.ComplaintStatus.RESOLVED);
        verify(repository).save(complaint);
    }

    @DisplayName("JUnit test for changeComplaintStatus operation (negative scenario)")
    @Test
    public void givenInvalidComplaintId_whenChangeStatus_thenThrowException() {
        given(repository.findById(1L)).willReturn(Optional.empty());

        assertThrows(ComplaintNotFoundException.class, () -> service.changeComplaintStatus(1L, Complaint.ComplaintStatus.RESOLVED));
    }
}
