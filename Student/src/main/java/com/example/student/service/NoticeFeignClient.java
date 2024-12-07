package com.example.student.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.student.dto.NoticeDto;

@FeignClient(name="Notice",url="http://localhost:8100/notices")
public interface NoticeFeignClient {

	@GetMapping("/get-all-notices")
	List<NoticeDto> findAll();
}
