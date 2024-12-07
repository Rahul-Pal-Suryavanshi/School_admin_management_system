package com.example.student.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.student.dto.CourseDto;



@FeignClient(name = "StudyStream",url = "http://localhost:8084")
public interface StudyStreamFiegnCient {
	
	@GetMapping("/courses/{id}")
	CourseDto findByCourseId(@PathVariable ("id") long id);
}
