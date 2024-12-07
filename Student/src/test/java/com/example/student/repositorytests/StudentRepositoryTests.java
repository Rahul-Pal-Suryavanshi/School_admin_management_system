package com.example.student.repositorytests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.student.dao.StudentRepository;
import com.example.student.entities.Student;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTests {
	
	
	@Autowired
	private StudentRepository repository;
	
	Student getDummyStudent() {
        Student student = new Student();
        student.setName("John Doe");
        student.setPhnNumber("1234567890");
        student.setAddress("123 Main St");
        student.setStandard("10");
        return student;
    }

    List<Student> getDummyStudentList() {
        Student student1 = getDummyStudent();
        Student student2 = new Student();
        student2.setName("Jane Smith");
        student2.setPhnNumber("0987654321");
        student2.setAddress("456 Elm St");
        student2.setStandard("11");
        return Arrays.asList(student1, student2);
    }

    @BeforeEach
    public void setup() {
    	
    }

    @Test
    @DisplayName("Unit test to save Student Operation")
    public void givenStudentObject_whenSave_thenReturnSavedStudent() {
        // Given Student Object
        Student student = getDummyStudent();
        // When
        Student savedStudent = repository.save(student);
        // Then verify output
        assertNotNull(savedStudent, "Student should not be null");
    }

    @DisplayName("JUnit test for get all students operation")
    @Test
    public void givenStudentsList_whenFindAll_thenStudentsList() {
        // given - precondition or setup
        getDummyStudentList().forEach(s -> repository.save(s));

        // when - action or the behaviour that we are going to test
        List<Student> studentList = repository.findAll();

        // then - verify the output
        Assertions.assertThat(studentList).isNotNull();
        Assertions.assertThat(studentList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for get Student by id operation")
    @Test
    public void givenStudentObject_whenFindById_thenReturnStudentObject() {
        // given - precondition or setup
        List<Student> list = getDummyStudentList();
        list.forEach(s -> repository.save(s));
        Student student = list.get(0);

        // when - action or the behaviour that we are going to test
        Optional<Student> foundStudent = repository.findById(student.getId());

        // then - verify the output
        assertNotNull(foundStudent.get(), "Student cannot be null");
    }

    @DisplayName("JUnit test for get Student by id operation Negative Test")
    @Test
    public void givenStudentObject_whenFindById_thenThrowException() {
        // given - precondition or setup
        List<Student> list = getDummyStudentList();
        list.forEach(s -> repository.save(s));

        // when - action or the behaviour that we are going to test
        Optional<Student> foundStudent = repository.findById(10L); // Assuming this ID does not exist

        // then - verify the output
        assertThrows(NoSuchElementException.class, foundStudent::get);
    }

    @DisplayName("JUnit test for update Student operation")
    @Test
    public void givenStudentObject_whenUpdateStudent_thenReturnUpdatedStudent() {
        // given - precondition or setup
        Student student = getDummyStudent();
        repository.save(student);

        // when - action or the behaviour that we are going to test
        Student savedStudent = repository.findById(student.getId()).get();
        savedStudent.setName("Updated Name");
        Student updatedStudent = repository.save(savedStudent);

        // then - verify the output
        Assertions.assertThat(updatedStudent.getName()).isEqualTo("Updated Name");
    }

    @DisplayName("JUnit test for delete Student operation")
    @Test
    public void givenStudentObject_whenDelete_thenRemoveStudent() {
        // given - precondition or setup
        Student student = getDummyStudent();
        repository.save(student);

        // when - action or the behaviour that we are going to test
        repository.deleteById(student.getId());
        Optional<Student> deletedStudent = repository.findById(student.getId());

        // then - verify the output
        Assertions.assertThat(deletedStudent).isEmpty();
    }
}
