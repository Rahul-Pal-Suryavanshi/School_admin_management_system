package com.example.studystream.servicetests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.studystream.dao.CourseRepository;
import com.example.studystream.dto.CourseDto;
import com.example.studystream.entities.Course;
import com.example.studystream.exception.CoursesNotFoundException;
import com.example.studystream.service.impl.CourseServiceImpl;


@ExtendWith(MockitoExtension.class)
public class CourseServiceTests {
	@Mock
    private CourseRepository repository;

    @InjectMocks
    private CourseServiceImpl service;

    Course getDummyCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setCoursename("Java Basics");
        course.setTeacherId(1L);
        return course;
    }

    List<Course> getDummyCourseList() {
        Course course1 = getDummyCourse();
        Course course2 = new Course();
        course2.setId(2L);
        course2.setCoursename("Python Basics");
        course2.setTeacherId(2L);
        return Arrays.asList(course1, course2);
    }

    @BeforeEach
    public void setup() {
        // Any necessary setup can be done here
    }

    @DisplayName("JUnit test for save Course method")
    @Test
    public void givenCourseObject_whenSaveCourse_thenReturnCourseObject() {
        // given - precondition or setup
        Course course = getDummyCourse();
        given(repository.save(course)).willReturn(course);

        // when - action or the behaviour that we are going to test
        Course savedCourse = service.save(course);

        // then - verify the output
        Assertions.assertThat(savedCourse).isNotNull();
    }

    @DisplayName("JUnit test for findAll method")
    @Test
    public void givenCoursesList_whenGetAllCourses_thenReturnCoursesList() {
        // given - precondition or setup
        given(repository.findAll()).willReturn(getDummyCourseList());

        // when - action or the behaviour that we are going to test
        List<Course> courseList = service.findAll();

        // then - verify the output
        Assertions.assertThat(courseList).isNotNull();
        Assertions.assertThat(courseList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for findAll method (negative scenario)")
    @Test
    public void givenEmptyCoursesList_whenGetAllCourses_thenReturnException() {
        // given - precondition or setup
        given(repository.findAll()).willReturn(Collections.emptyList());

     // when & then - action or the behaviour that we are going to test
        assertThrows(CoursesNotFoundException.class, () -> service.findAll());
    }

    @DisplayName("JUnit test for findById method Exception")
    @Test
    public void givenCourseId_whenGetCourseById_thenReturnException() {
        // given
        given(repository.findById(1L)).willReturn(Optional.empty());

        // when & then
        assertThrows(CoursesNotFoundException.class, () -> service.findById(1L));
    }

    
    @DisplayName("JUnit test for deleteById method")
    @Test
    public void givenCourseId_whenDeleteCourse_thenNothing() {
        // given - precondition or setup
        long Id = 1L;
        Course course = getDummyCourse();
        given(repository.findById(1L)).willReturn(Optional.of(course));
        willDoNothing().given(repository).delete(course);

        // when - action or the behaviour that we are going to test
        service.delete(Id);

        // then - verify the output
        verify(repository, times(1)).delete(course);
    }
}
