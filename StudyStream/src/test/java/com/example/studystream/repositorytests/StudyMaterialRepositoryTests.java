package com.example.studystream.repositorytests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.studystream.dao.StudyMaterialRepository;
import com.example.studystream.entities.StudyMaterial;
import com.example.studystream.exception.StudyMaterialNotFoundException;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudyMaterialRepositoryTests {
	@Autowired
    private StudyMaterialRepository studyMaterialRepository;

    private StudyMaterial studyMaterial;

    @BeforeEach
    public void setup() {
        studyMaterial = new StudyMaterial();
        studyMaterial.setMaterialname("Sample Material");
        studyMaterial.setStandard("10");
    }

    @Test
    @DisplayName("JUnit test for saving StudyMaterial")
    public void givenStudyMaterial_whenSave_thenReturnSavedStudyMaterial() {
        // When
        StudyMaterial savedMaterial = studyMaterialRepository.save(studyMaterial);

        // Then
        assertThat(savedMaterial).isNotNull();
        assertThat(savedMaterial.getId()).isNotNull();
        assertThat(savedMaterial.getMaterialname()).isEqualTo("Sample Material");
        assertThat(savedMaterial.getStandard()).isEqualTo("10");
    }

    @Test
    @DisplayName("JUnit test for findById method")
    public void givenStudyMaterialId_whenFindById_thenReturnStudyMaterial() {
        // Given
        StudyMaterial savedMaterial = studyMaterialRepository.save(studyMaterial);

        // When
        Optional<StudyMaterial> foundMaterial = studyMaterialRepository.findById(savedMaterial.getId());

        // Then
        assertThat(foundMaterial).isPresent();
        assertThat(foundMaterial.get().getMaterialname()).isEqualTo("Sample Material");
    }

    @Test
    @DisplayName("JUnit test for findById method when StudyMaterial does not exist")
    public void givenInvalidId_whenFindById_thenThrowException() {
        // When & Then
        assertThrows(StudyMaterialNotFoundException.class, () -> {
            studyMaterialRepository.findById(999L).orElseThrow(() -> 
                new StudyMaterialNotFoundException("StudyMaterial not found with id: 999"));
        });
    }

    @Test
    @DisplayName("JUnit test for delete method")
    public void givenStudyMaterialId_whenDelete_thenRemoveStudyMaterial() {
        // Given
        StudyMaterial savedMaterial = studyMaterialRepository.save(studyMaterial);

        // When
        studyMaterialRepository.deleteById(savedMaterial.getId());

        // Then
        Optional<StudyMaterial> foundMaterial = studyMaterialRepository.findById(savedMaterial.getId());
        assertThat(foundMaterial).isNotPresent();
    }

    @Test
    @DisplayName("JUnit test for finding all StudyMaterials")
    public void whenFindAll_thenReturnListOfStudyMaterials() {
        // Given
        studyMaterialRepository.save(studyMaterial);

        // When
        List<StudyMaterial> materials = studyMaterialRepository.findAll();

        // Then
        assertThat(materials).isNotNull();
        assertThat(materials.size()).isGreaterThan(0);
    }
}
