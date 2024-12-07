package com.example.Teacher.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Teacher.dao.TeacherRepository;
import com.example.Teacher.dto.CourseDto;
import com.example.Teacher.dto.NoticeDetailsDto;
import com.example.Teacher.dto.NoticeDto;
import com.example.Teacher.dto.StudentDto;
import com.example.Teacher.dto.StudyMaterial;
import com.example.Teacher.dto.StudyMaterialDto;
import com.example.Teacher.entities.Teacher;
import com.example.Teacher.exception.NoticesNotFoundException;
import com.example.Teacher.exception.StudentsNotFoundException;
import com.example.Teacher.exception.StudyMaterialsNotFoundException;
import com.example.Teacher.exception.TeacherNotFoundException;
import com.example.Teacher.service.NoticeFeignClient;
import com.example.Teacher.service.StudentFeignClient;
import com.example.Teacher.service.StudyStreamFeignClient;
import com.example.Teacher.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	StudentFeignClient studentClient;
	
	@Autowired
	StudyStreamFeignClient studyStreamFeignClient;
	
	@Autowired
	NoticeFeignClient noticeFeignClient;

	@Override
	public Teacher save(Teacher teacher) {
		return teacherRepository.save(teacher);
	}
	
	@Override
	public List<Teacher> findAll() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher findById(long id) {
		return teacherRepository.findById(id).orElseThrow(() -> 
        						new TeacherNotFoundException("Teacher not found with id: " + id));
	}

	@Override
	public Teacher updateTeacherById(long id, Teacher teacher) {
		Teacher oldData = findById(id);
		oldData.setName(teacher.getName());
		oldData.setEmail(teacher.getEmail());
		oldData.setClassTeacherOf(teacher.getClassTeacherOf());
		oldData.setQualification(teacher.getQualification());
		oldData.setAddress(teacher.getAddress());
		save(oldData);
		return oldData ;
	}

	@Override
	public void deleteById(long id) {
		Teacher teacher = findById(id);
		teacherRepository.delete(teacher);
	}
	
	public List<StudentDto> findByStandard(@PathVariable("standard") String standard){
		List<StudentDto> listOfStudents = studentClient.findByStandard(standard);
		if (listOfStudents.isEmpty()) {
	        throw new StudentsNotFoundException("No students found for standard: " + standard);
	    }
		return listOfStudents;
	}
	
	@Override
	public void addStudyMaterial(Long courseId, StudyMaterial studyMaterial) {
		studyStreamFeignClient.addStudyMaterial(courseId, studyMaterial);
	}
	

	@Override
	public NoticeDto postNotice(NoticeDetailsDto noticeDetailsDto) {
		return noticeFeignClient.save(noticeDetailsDto);
	}

	@Override
	public List<NoticeDto> viewAllNotices() {
		List<NoticeDto> notices = noticeFeignClient.findAll();
		if (notices == null || notices.isEmpty()) {
			throw new NoticesNotFoundException("No notices found.");
		}

		return notices;
	}

	@Override
	public Set<StudyMaterialDto> viewStudyMaterials(long courseId) {
		CourseDto course = studyStreamFeignClient.findByCourseId(courseId);
		Set<StudyMaterialDto> materials = course.getStudyMaterialDtos();
		if(materials == null) {
			throw new StudyMaterialsNotFoundException("StudyMaterials are not found for given course id "+courseId);
		}
		return materials;
	}

	

	

}
