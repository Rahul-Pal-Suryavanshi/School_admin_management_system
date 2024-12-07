package com.example.Teacher.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Teacher.dto.NoticeDetailsDto;
import com.example.Teacher.dto.NoticeDto;



@FeignClient(name="Notice",url="http://localhost:8100/notices")
public interface NoticeFeignClient {
	
	@PostMapping("/post")
	NoticeDto save(@RequestBody NoticeDetailsDto noticeDetailsDto);
	
	@GetMapping("/get-all-notices")
	List<NoticeDto> findAll();
	
}
