package com.example.Complaint.repositorytests;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Complaint.dao.ComplaintRepository;
import com.example.Complaint.entities.Complaint;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComplaintRepositoryTests {
	@Autowired
    private ComplaintRepository repository;

    @BeforeEach
    public void setup() {
        // Any setup can be done here if needed
    }

    @Test
    @DisplayName("Unit test to save Complaint operation")
    public void givenComplaintObject_whenSave_thenReturnSavedComplaint() {
        // Given Complaint Object
        Complaint complaint = new Complaint();
        complaint.setStudentId(1L);
        complaint.setSubject("Test Subject");
        complaint.setDescription("This is a test complaint.");

        // When
        Complaint savedComplaint = repository.save(complaint);

        // Then verify output
        assertNotNull(savedComplaint, "Complaint should not be null");
    }

    @DisplayName("JUnit test for get all complaints operation")
    @Test
    public void givenComplaintsList_whenFindAll_thenComplaintsList() {
        // Given precondition or setup
        Complaint complaint1 = new Complaint();
        complaint1.setStudentId(1L);
        complaint1.setSubject("Complaint 1");
        complaint1.setDescription("Description for complaint 1.");

        Complaint complaint2 = new Complaint();
        complaint2.setStudentId(2L);
        complaint2.setSubject("Complaint 2");
        complaint2.setDescription("Description for complaint 2.");

        repository.save(complaint1);
        repository.save(complaint2);

        // When - action or the behaviour that we are going to test
        List<Complaint> complaintList = repository.findAll();

        // Then - verify the output
        Assertions.assertThat(complaintList).isNotNull();
        Assertions.assertThat(complaintList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for get Complaint by id operation")
    @Test
    public void givenComplaintId_whenFindById_thenReturnComplaintObject() {
        // Given precondition or setup
        Complaint complaint = new Complaint();
        complaint.setStudentId(1L);
        complaint.setSubject("Complaint 1");
        complaint.setDescription("Description for complaint 1.");
        repository.save(complaint);

        // When - action or the behaviour that we are going to test     
        Optional<Complaint> foundComplaint = repository.findById(complaint.getId());

        // Then - verify the output
        assertNotNull(foundComplaint.get(), "Complaint cannot be null");
    }

    @DisplayName("JUnit test for get Complaint by id operation Negative Test")
    @Test
    public void givenInvalidComplaintId_whenFindById_thenThrowException() {
        // Given precondition or setup
        Complaint complaint = new Complaint();
        complaint.setStudentId(1L);
        complaint.setSubject("Complaint 1");
        complaint.setDescription("Description for complaint 1.");
        repository.save(complaint);

        // When - action or the behaviour that we are going to test     
        Optional<Complaint> foundComplaint = repository.findById(10L); // assuming 10L is invalid

        // Then - verify the output
        assertThrows(NoSuchElementException.class, () -> foundComplaint.get());
    }

    @DisplayName("JUnit test for delete Complaint operation")
    @Test
    public void givenComplaintObject_whenDelete_thenRemoveComplaint() {
        // Given precondition or setup
        Complaint complaint = new Complaint();
        complaint.setStudentId(1L);
        complaint.setSubject("Complaint to delete");
        complaint.setDescription("This complaint will be deleted.");
        repository.save(complaint);

        // When - action or the behaviour that we are going to test
        repository.deleteById(complaint.getId());
        Optional<Complaint> foundComplaint = repository.findById(complaint.getId());

        // Then - verify the output
        Assertions.assertThat(foundComplaint).isEmpty();
    }
}
