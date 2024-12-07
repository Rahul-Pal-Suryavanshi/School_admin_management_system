package com.example.studystream.mapper;


import java.util.Set;
import java.util.stream.Collectors;

import com.example.studystream.dto.CourseDto;
import com.example.studystream.dto.StudyMaterialDto;
import com.example.studystream.entities.Course;
import com.example.studystream.entities.StudyMaterial;

public class StudyStreamMapper {
	public static StudyMaterialDto materialToMaterialDto(StudyMaterial studyMaterial) {
		StudyMaterialDto studyMaterialDto = new StudyMaterialDto();
		studyMaterialDto.setId(studyMaterial.getId());
		studyMaterialDto.setMaterialname(studyMaterial.getMaterialname());
		studyMaterialDto.setStandard(studyMaterial.getStandard());
		return studyMaterialDto;
	}
	
	public static CourseDto courseToCourseDto(Course course) {
		
		CourseDto courseDto = new CourseDto();
		courseDto.setId(course.getId());
		courseDto.setCoursename(course.getCoursename());
		courseDto.setTeacherId(course.getTeacherId());
		Set<StudyMaterial> ls = course.getStudyMaterials();
		Set<StudyMaterialDto> ls1= ls.stream().map(StudyStreamMapper::materialToMaterialDto).collect(Collectors.toSet());
		courseDto.setStudyMaterialDtos(ls1);
		return courseDto;
		
	}
}
