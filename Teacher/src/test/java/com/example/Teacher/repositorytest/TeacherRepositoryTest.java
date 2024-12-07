package com.example.Teacher.repositorytest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.Teacher.dao.TeacherRepository;
import com.example.Teacher.entities.Teacher;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeacherRepositoryTest {
	@Autowired
    private TeacherRepository repository;

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
    public void givenTeacherObject_whenSave_thenReturnSavedTeacher() {
        Teacher savedTeacher = repository.save(teacher);
        assertThat(savedTeacher).isNotNull();
        assertThat(savedTeacher.getId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for findAll operation")
    @Test
    public void whenFindAll_thenReturnTeacherList() {
        repository.save(teacher);

        List<Teacher> teachers = repository.findAll();
        assertThat(teachers).isNotEmpty();
        assertThat(teachers.size()).isEqualTo(1);
    }

    @DisplayName("JUnit test for findById operation")
    @Test
    public void whenFindById_thenReturnTeacher() {
        Teacher savedTeacher = repository.save(teacher);
        Long id = savedTeacher.getId();

        Teacher foundTeacher = repository.findById(id).orElse(null);
        assertThat(foundTeacher).isNotNull();
        assertThat(foundTeacher.getName()).isEqualTo("John Doe");
    }

    @DisplayName("JUnit test for delete operation")
    @Test
    public void whenDelete_thenTeacherShouldBeRemoved() {
        Teacher savedTeacher = repository.save(teacher);
        Long id = savedTeacher.getId();

        repository.delete(savedTeacher);
        Optional<Teacher> foundTeacher = repository.findById(id);
        assertThat(foundTeacher).isEmpty();
    }
}
