package com.example.Teacher.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Teacher.dto.StudentDto;


@FeignClient (url = "http://localhost:8082/students",name="stud",value="stud")
public interface StudentFeignClient {

	@GetMapping("/getstudentbystandard/{standard}")
	List<StudentDto> findByStandard(@PathVariable("standard") String standard);
	
	
}
