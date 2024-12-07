package com.example.Teacher.servicetests;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

import com.example.Teacher.dao.TeacherRepository;
import com.example.Teacher.entities.Teacher;
import com.example.Teacher.exception.TeacherNotFoundException;
import com.example.Teacher.service.impl.TeacherServiceImpl;


@ExtendWith(MockitoExtension.class)
public class TeacherServiceTests {
	@Mock
    private TeacherRepository repository;

    @InjectMocks
    private TeacherServiceImpl service;

    private Teacher teacher;

    @BeforeEach
    public void setup() {
        teacher = new Teacher();
        teacher.setName("John Doe");
        teacher.setEmail("john.doe@example.com");
        teacher.setClassTeacherOf("10");
        teacher.setQualification("MSc");
        teacher.setAddress("123 Main St");
    }

    @DisplayName("JUnit test for save Teacher operation")
    @Test
    public void givenTeacherObject_whenSave_thenReturnTeacherObject() {
        given(repository.save(teacher)).willReturn(teacher);

        Teacher savedTeacher = service.save(teacher);

        assertThat(savedTeacher).isNotNull();
    }

    @DisplayName("JUnit test for findById operation")
    @Test
    public void givenTeacherId_whenFindById_thenReturnTeacherObject() {
        given(repository.findById(1L)).willReturn(Optional.of(teacher));

        Teacher foundTeacher = service.findById(1L);

        assertThat(foundTeacher).isNotNull();
        assertThat(foundTeacher.getName()).isEqualTo("John Doe");
    }

    @DisplayName("JUnit test for findById operation (negative scenario)")
    @Test
    public void givenInvalidTeacherId_whenFindById_thenThrowException() {
        given(repository.findById(1L)).willReturn(Optional.empty());

        assertThrows(TeacherNotFoundException.class, () -> service.findById(1L));
    }

    @DisplayName("JUnit test for findAll operation")
    @Test
    public void whenFindAll_thenReturnTeacherList() {
        given(repository.findAll()).willReturn(Collections.singletonList(teacher));

        List<Teacher> teachers = service.findAll();

        assertThat(teachers).isNotEmpty();
        assertThat(teachers.size()).isEqualTo(1);
    }

    @DisplayName("JUnit test for updateTeacherById operation")
    @Test
    public void givenTeacherIdAndTeacherObject_whenUpdate_thenReturnUpdatedTeacher() {
        given(repository.findById(1L)).willReturn(Optional.of(teacher));
        given(repository.save(teacher)).willReturn(teacher);

        Teacher updatedTeacher = service.updateTeacherById(1L, teacher);

        assertThat(updatedTeacher).isNotNull();
        assertThat(updatedTeacher.getName()).isEqualTo("John Doe");
    }

    @DisplayName("JUnit test for deleteById operation")
    @Test
    public void givenTeacherId_whenDelete_thenRemoveTeacher() {
        given(repository.findById(1L)).willReturn(Optional.of(teacher));
        doNothing().when(repository).delete(teacher);

        service.deleteById(1L);

        verify(repository, times(1)).delete(teacher);
    }
}
