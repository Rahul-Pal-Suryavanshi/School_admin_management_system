package com.example.student.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.student.dto.HomeworkDto;

@FeignClient(name = "Teacher",url = "http://localhost:8080")
public interface TeacherFeignClient {
	
	@GetMapping("/homeworks/getHomeworkByStandard/{standard}")
	List<HomeworkDto> getHomeworkByStandard(@PathVariable("standard") String standard);
}
