package com.example.student.servicetests;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.willDoNothing;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.example.student.dao.StudentRepository;
import com.example.student.entities.Student;
import com.example.student.exception.ResourceNotFoundException;
import com.example.student.service.impl.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTests {
	
	@Mock
	private StudentRepository repository;
	
	@InjectMocks
	private StudentServiceImpl service;
	
	Student getDummyStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setPhnNumber("1234567890");
        student.setAddress("123 Main St");
        student.setStandard("10");
        return student;
    }

    List<Student> getDummyStudentList() {
        Student student1 = getDummyStudent();
        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jane Smith");
        student2.setPhnNumber("0987654321");
        student2.setAddress("456 Elm St");
        student2.setStandard("11");
        return Arrays.asList(student1, student2);
    }

    @BeforeEach
    public void setup() {
        // Any necessary setup can be done here
    }

    // JUnit test for save method
    @DisplayName("JUnit test for save Student method")
    @Test
    public void givenStudentObject_whenSaveStudent_thenReturnStudentObject() {
        // given - precondition or setup
        Student student = getDummyStudent();
        when(repository.save(student)).thenReturn(student);

        // when - action or the behaviour that we are going to test
        Student savedStudent = service.save(student);

        // then - verify the output
        Assertions.assertThat(savedStudent).isNotNull();
    }

    @DisplayName("JUnit test for findAll method")
    @Test
    public void givenStudentsList_whenGetAllStudents_thenReturnStudentsList() {
        // given - precondition or setup
        when(repository.findAll()).thenReturn(getDummyStudentList());

        // when - action or the behaviour that we are going to test
        List<Student> studentList = service.findAll();

        // then - verify the output
        Assertions.assertThat(studentList).isNotNull();
        Assertions.assertThat(studentList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAll method (negative scenario)")
    @Test
    public void givenEmptyStudentsList_whenGetAllStudents_thenReturnEmptyStudentsList() {
        // given - precondition or setup
        given(repository.findAll()).willReturn(Collections.emptyList());

        // when - action or the behaviour that we are going to test
        List<Student> studentList = service.findAll();

        // then - verify the output
        Assertions.assertThat(studentList).isEmpty();
        Assertions.assertThat(studentList.size()).isEqualTo(0);
    }

    @DisplayName("JUnit test for findById method")
    @Test
    public void givenStudentId_whenGetStudentById_thenReturnStudentObject() {
        // given
        given(repository.findById(1L)).willReturn(Optional.of(getDummyStudent()));

        // when
        Student savedStudent = service.findById(1L);

        // then
        Assertions.assertThat(savedStudent).isNotNull();
    }

    @DisplayName("JUnit test for findById method Exception")
    @Test
    public void givenStudentId_whenGetStudentById_thenReturnException() {
        // given
        given(repository.findById(1L)).willReturn(Optional.empty());

        // when / then
        assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
    }

    @DisplayName("JUnit test for deleteById method")
    @Test
    public void givenStudentId_whenDeleteStudent_thenNothing() {
        // given - precondition or setup
        long id = 1L;
        Student student = getDummyStudent();
        given(repository.findById(1L)).willReturn(Optional.of(student));
        willDoNothing().given(repository).delete(student);

        // when - action or the behaviour that we are going to test
        service.deleteById(id);

        // then - verify the output
        verify(repository, times(1)).delete(student);
    }
}
