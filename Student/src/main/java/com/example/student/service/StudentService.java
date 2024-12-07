package com.example.student.service;

import java.util.List;
import java.util.Set;

import com.example.student.dto.ComplaintDetailsDto;
import com.example.student.dto.ComplaintDto;
import com.example.student.dto.HomeworkDto;
import com.example.student.dto.NoticeDto;
import com.example.student.dto.StudyMaterialDto;
import com.example.student.entities.Student;

public interface StudentService {
	Student save(Student student);
	List<Student> findAll();
	Student findById(long id);
	Student updateTeacherById(long id,Student student);
	void deleteById(long id);
	
	List<Student> findByStandard(String standard);
	
	ComplaintDto raiseComplaint(ComplaintDetailsDto complaintdeaDetailsDto,long studentId);

	Set<StudyMaterialDto> getCourseWithMaterialsOfSpecificStandard(long id,String standard);
	
	List<NoticeDto> getAllNotices();
	
	List<HomeworkDto> getHomeworkByStandard(String standard);
}
