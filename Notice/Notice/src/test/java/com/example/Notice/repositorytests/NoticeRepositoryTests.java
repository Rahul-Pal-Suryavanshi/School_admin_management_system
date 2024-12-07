package com.example.Notice.repositorytests;
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

import com.example.Notice.dao.NoticeRepository;
import com.example.Notice.entities.Notice;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NoticeRepositoryTests {
	@Autowired
    private NoticeRepository repository;

    @BeforeEach
    public void setup() {
        // any setup can be done here if needed
    }

    @Test
    @DisplayName("Unit test to save Notice operation")
    public void givenNoticeObject_whenSave_thenReturnSavedNotice() {
        // Given Notice Object
        Notice notice = new Notice();
        notice.setTitle("Test Notice");
        notice.setContent("This is a test notice.");
        // When
        Notice savedNotice = repository.save(notice);
        // Then verify output
        assertNotNull(savedNotice, "Notice should not be null");
    }

    @DisplayName("JUnit test for get all notices operation")
    @Test
    public void givenNoticesList_whenFindAll_thenNoticesList() {
        // Given precondition or setup
        Notice notice1 = new Notice();
        notice1.setTitle("Notice 1");
        notice1.setContent("Content for notice 1.");
        
        Notice notice2 = new Notice();
        notice2.setTitle("Notice 2");
        notice2.setContent("Content for notice 2.");
        
        repository.save(notice1);
        repository.save(notice2);

        // When - action or the behaviour that we are going to test
        List<Notice> noticeList = repository.findAll();

        // Then - verify the output
        Assertions.assertThat(noticeList).isNotNull();
        Assertions.assertThat(noticeList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for get Notice by id operation")
    @Test
    public void givenNoticeObject_whenFindById_thenReturnNoticeObject() {
        // Given precondition or setup
        Notice notice = new Notice();
        notice.setTitle("Notice 1");
        notice.setContent("Content for notice 1.");
        repository.save(notice);
        
        // When - action or the behaviour that we are going to test     
        Optional<Notice> foundNotice = repository.findById(notice.getId());

        // Then - verify the output
        assertNotNull(foundNotice.get(), "Notice cannot be null");
    }

    @DisplayName("JUnit test for get Notice by id operation Negative Test")
    @Test
    public void givenInvalidId_whenFindById_thenThrowException() {
        // Given precondition or setup
        Notice notice = new Notice();
        notice.setTitle("Notice 1");
        notice.setContent("Content for notice 1.");
        repository.save(notice);
        
        // When - action or the behaviour that we are going to test     
        Optional<Notice> foundNotice = repository.findById(10L); // assuming 10L is invalid

        // Then - verify the output
        assertThrows(NoSuchElementException.class, () -> foundNotice.get());
    }

    @DisplayName("JUnit test for update Notice operation")
    @Test
    public void givenNoticeObject_whenUpdateNotice_thenReturnUpdatedNotice() {
        // Given precondition or setup
        Notice notice = new Notice();
        notice.setTitle("Notice 1");
        notice.setContent("Content for notice 1.");
        Notice savedNotice = repository.save(notice);

        // When - action or the behaviour that we are going to test
        savedNotice.setTitle("Updated Notice");
        Notice updatedNotice = repository.save(savedNotice);

        // Then - verify the output
        Assertions.assertThat(updatedNotice.getTitle()).isEqualTo("Updated Notice");
    }

    @DisplayName("JUnit test for delete Notice operation")
    @Test
    public void givenNoticeObject_whenDelete_thenRemoveNotice() {
        // Given precondition or setup
        Notice notice = new Notice();
        notice.setTitle("Notice to delete");
        notice.setContent("This notice will be deleted.");
        repository.save(notice);

        // When - action or the behaviour that we are going to test
        repository.deleteById(notice.getId());
        Optional<Notice> foundNotice = repository.findById(notice.getId());

        // Then - verify the output
        Assertions.assertThat(foundNotice).isEmpty();
    }
}
