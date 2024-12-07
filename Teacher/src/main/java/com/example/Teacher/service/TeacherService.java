package com.example.Teacher.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Teacher.dto.NoticeDetailsDto;
import com.example.Teacher.dto.NoticeDto;
import com.example.Teacher.dto.StudentDto;
import com.example.Teacher.dto.StudyMaterial;
import com.example.Teacher.dto.StudyMaterialDto;
import com.example.Teacher.entities.Teacher;



public interface TeacherService {
	
	Teacher save(Teacher teacher);
	List<Teacher> findAll();
	Teacher findById(long id);
	Teacher updateTeacherById(long id,Teacher teacher);
	void deleteById(long id);
	
	List<StudentDto> findByStandard(String standard);
	
	void addStudyMaterial(Long courseId,StudyMaterial studyMaterial);
	
	Set<StudyMaterialDto> viewStudyMaterials(long courseId);
	
	NoticeDto postNotice(NoticeDetailsDto noticeDetailsDto);
	
	List<NoticeDto> viewAllNotices();
	
	
}
