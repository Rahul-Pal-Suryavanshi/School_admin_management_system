package com.example.Teacher.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Teacher.dto.CourseDto;
import com.example.Teacher.dto.StudyMaterial;



@FeignClient (name = "StudyStream",url = "http://localhost:8084")
public interface StudyStreamFeignClient {
	
	@PostMapping("/study-materials/add-studymaterials/courses/{courseId}")
	StudyMaterial addStudyMaterial(@PathVariable("courseId") Long courseId, @RequestBody StudyMaterial studyMaterial);
	
	@GetMapping("/courses/{id}")
	CourseDto findByCourseId(@PathVariable ("id") long id);
}
