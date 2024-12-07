package com.example.studystream.repositorytests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
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

import com.example.studystream.dao.CourseRepository;
import com.example.studystream.entities.Course;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepositoryTests {
	
	@Autowired
    private CourseRepository repository;
	
	Course getDummyCourse() {
        Course course = new Course();
        course.setCoursename("Java Basics");
        course.setTeacherId(1L);
        return course;
    }

    List<Course> getDummyCourseList() {
        Course course1 = getDummyCourse();
        Course course2 = new Course();
        course2.setCoursename("Python Basics");
        course2.setTeacherId(2L);
        return Arrays.asList(course1, course2);
    }

    @BeforeEach
    public void setup() {
        // Any necessary setup can be done here
    }

    @Test
    @DisplayName("Unit test to save Course Operation")
    public void givenCourseObject_whenSave_thenReturnSavedCourse() {
        // Given Course Object
        Course course = getDummyCourse();
        // When
        Course savedCourse = repository.save(course);
        // Then verify output
        assertNotNull(savedCourse, "Course should not be null");
    }

    @DisplayName("JUnit test for get all courses operation")
    @Test
    public void givenCoursesList_whenFindAll_thenCoursesList() {
        // given - precondition or setup
        getDummyCourseList().forEach(course -> repository.save(course));

        // when - action or the behaviour that we are going to test
        List<Course> courseList = repository.findAll();

        // then - verify the output
        Assertions.assertThat(courseList).isNotNull();
        Assertions.assertThat(courseList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for get Course by id operation")
    @Test
    public void givenCourseObject_whenFindById_thenReturnCourseObject() {
        // given - precondition or setup
        Course course = getDummyCourse();
        Course savedCourse = repository.save(course);

        // when - action or the behaviour that we are going to test
        Optional<Course> foundCourse = repository.findById(savedCourse.getId());

        // then - verify the output
        assertNotNull(foundCourse.get(), "Course cannot be null");
    }

    @DisplayName("JUnit test for get Course by id operation Negative Test")
    @Test
    public void givenInvalidId_whenFindById_thenThrowException() {
        // given - precondition or setup
        // no setup required for invalid id

        // when - action or the behaviour that we are going to test
        Optional<Course> foundCourse = repository.findById(10L);

        // then - verify the output
        assertThrows(NoSuchElementException.class, () -> foundCourse.get());
    }

    @DisplayName("JUnit test for update Course operation")
    @Test
    public void givenCourseObject_whenUpdateCourse_thenReturnUpdatedCourse() {
        // given - precondition or setup
        Course course = getDummyCourse();
        Course savedCourse = repository.save(course);

        // when - action or the behaviour that we are going to test
        savedCourse.setCoursename("Advanced Java");
        Course updatedCourse = repository.save(savedCourse);

        // then - verify the output
        Assertions.assertThat(updatedCourse.getCoursename()).isEqualTo("Advanced Java");
    }

    @DisplayName("JUnit test for delete Course operation")
    @Test
    public void givenCourseObject_whenDelete_thenRemoveCourse() {
        // given - precondition or setup
        Course course = getDummyCourse();
        Course savedCourse = repository.save(course);

        // when - action or the behaviour that we are going to test
        repository.deleteById(savedCourse.getId());
        Optional<Course> optionalCourse = repository.findById(savedCourse.getId());

        // then - verify the output
        Assertions.assertThat(optionalCourse).isEmpty();
    }
}
