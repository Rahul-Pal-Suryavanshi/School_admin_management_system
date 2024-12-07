package com.example.student.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.student.dao.StudentRepository;
import com.example.student.dto.ComplaintDetailsDto;
import com.example.student.dto.ComplaintDto;
import com.example.student.dto.CourseDto;
import com.example.student.dto.HomeworkDto;
import com.example.student.dto.NoticeDto;
import com.example.student.dto.StudyMaterialDto;
import com.example.student.entities.Student;
import com.example.student.exception.ResourceNotFoundException;
import com.example.student.service.ComplaintFeignClient;
import com.example.student.service.NoticeFeignClient;
import com.example.student.service.StudentService;
import com.example.student.service.StudyStreamFiegnCient;
import com.example.student.service.TeacherFeignClient;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	ComplaintFeignClient complaintFeignClient;

	@Autowired
	StudyStreamFiegnCient studyStreamFiegnCient;

	@Autowired
	NoticeFeignClient noticeFeignClient;

	@Autowired
	TeacherFeignClient teacherFeignClient;

	@Override
	public Student save(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	@Override
	public Student findById(long id) {
		return studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found with id: " + id));
	}

	@Override
	public Student updateTeacherById(long id, Student student) {
		Student oldStudent = findById(id);
		oldStudent.setName(student.getName());
		oldStudent.setStandard(student.getStandard());
		oldStudent.setPhnNumber(student.getPhnNumber());
		oldStudent.setAddress(student.getAddress());
		return oldStudent;
	}

	@Override
	public void deleteById(long id) {
		Student student = findById(id);
		studentRepository.delete(student);
	}

	@Override
	public List<Student> findByStandard(String standard) {
		
		return studentRepository.findByStandard(standard);
	}

	@Override
	public ComplaintDto raiseComplaint(ComplaintDetailsDto complaintdeaDetailsDto, long studentId) {
		return complaintFeignClient.raiseComplaint(complaintdeaDetailsDto, studentId);
	}

	@Override
	public Set<StudyMaterialDto> getCourseWithMaterialsOfSpecificStandard(long id, String standard) {

		CourseDto courseDto = studyStreamFiegnCient.findByCourseId(id);
		if (courseDto == null) {
			throw new ResourceNotFoundException("Course not found with id: " + id);
		}

		Set<StudyMaterialDto> filteredMaterials = courseDto.getStudyMaterialDtos().stream()
				.filter(s -> s.getStandard().equals(standard)).collect(Collectors.toSet());
		if (filteredMaterials.isEmpty()) {
			throw new ResourceNotFoundException("No study materials found for the specified standard. They will be updated soon.");
		}

		return filteredMaterials;
	}

	@Override
	public List<NoticeDto> getAllNotices() {
		List<NoticeDto> notices = noticeFeignClient.findAll();

		if (notices == null || notices.isEmpty()) {
			throw new ResourceNotFoundException("No notices found.");
		}

		return notices;
	}

	@Override
	public List<HomeworkDto> getHomeworkByStandard(String standard) {
		
		List<HomeworkDto> homeworkList = teacherFeignClient.getHomeworkByStandard(standard);
		
		if (homeworkList == null || homeworkList.isEmpty()) {
			throw new ResourceNotFoundException("No homeworks found for standard: " + standard);
		}
		return homeworkList;
	}

}
