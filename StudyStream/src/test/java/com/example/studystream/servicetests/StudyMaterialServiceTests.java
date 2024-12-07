package com.example.studystream.servicetests;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.studystream.dao.StudyMaterialRepository;
import com.example.studystream.entities.StudyMaterial;
import com.example.studystream.exception.StudyMaterialNotFoundException;
import com.example.studystream.service.impl.StudyMaterialServiceImpl;


@ExtendWith(MockitoExtension.class)
public class StudyMaterialServiceTests {
	@Mock
    private StudyMaterialRepository studyMaterialRepository;

    @InjectMocks
    private StudyMaterialServiceImpl studyMaterialService;

    private StudyMaterial studyMaterial;

    @BeforeEach
    public void setup() {
        studyMaterial = new StudyMaterial();
        studyMaterial.setId(1L);
        studyMaterial.setMaterialname("Algebra Notes");
        studyMaterial.setStandard("10");
    }

    @DisplayName("JUnit test for save study material operation")
    @Test
    public void givenStudyMaterialObject_whenSave_thenReturnSavedStudyMaterial() {
        given(studyMaterialRepository.save(studyMaterial)).willReturn(studyMaterial);

        StudyMaterial savedMaterial = studyMaterialService.save(studyMaterial);

        assertThat(savedMaterial).isNotNull();
        assertThat(savedMaterial.getMaterialname()).isEqualTo("Algebra Notes");
    }

    @DisplayName("JUnit test for findById operation")
    @Test
    public void givenStudyMaterialId_whenFindById_thenReturnStudyMaterial() {
        given(studyMaterialRepository.findById(1L)).willReturn(Optional.of(studyMaterial));

        StudyMaterial foundMaterial = studyMaterialService.findById(1L);

        assertThat(foundMaterial).isNotNull();
        assertThat(foundMaterial.getMaterialname()).isEqualTo("Algebra Notes");
    }

    @DisplayName("JUnit test for findById operation (not found case)")
    @Test
    public void givenInvalidStudyMaterialId_whenFindById_thenThrowStudyMaterialNotFoundException() {
        given(studyMaterialRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(StudyMaterialNotFoundException.class, () -> studyMaterialService.findById(1L));
    }

    @DisplayName("JUnit test for findAll operation")
    @Test
    public void whenFindAll_thenReturnStudyMaterialsList() {
        given(studyMaterialRepository.findAll()).willReturn(Arrays.asList(studyMaterial));

        List<StudyMaterial> materials = studyMaterialService.findAll();

        assertThat(materials).isNotEmpty();
        assertThat(materials.size()).isEqualTo(1);
    }

    @DisplayName("JUnit test for delete operation")
    @Test
    public void givenStudyMaterialId_whenDelete_thenRemoveStudyMaterial() {
        given(studyMaterialRepository.findById(1L)).willReturn(Optional.of(studyMaterial));

        studyMaterialService.delete(1L);

        verify(studyMaterialRepository).delete(studyMaterial);
    }

    @DisplayName("JUnit test for delete operation (not found case)")
    @Test
    public void givenInvalidStudyMaterialId_whenDelete_thenThrowStudyMaterialNotFoundException() {
        given(studyMaterialRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(StudyMaterialNotFoundException.class, () -> studyMaterialService.delete(1L));
    }
}
