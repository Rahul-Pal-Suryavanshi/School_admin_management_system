package com.example.Notice.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Notice.dao.NoticeRepository;
import com.example.Notice.dto.NoticeDetails;
import com.example.Notice.entities.Notice;
import com.example.Notice.exception.ResourceNotFoundException;
import com.example.Notice.service.NoticeService;

@Service
public class NoticeServicempl implements NoticeService{
	
	@Autowired
	NoticeRepository noticeRepository;

	@Override
	public Notice save(NoticeDetails noticeDetails) {
		Notice notice = new Notice();
		notice.setTitle(noticeDetails.getTitle());
		notice.setContent(noticeDetails.getContent());
		notice.setDatePosted(LocalDateTime.now());
		return noticeRepository.save(notice);
	}

	@Override
	public Notice findById(long id) {
		return noticeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Notice not found with id: " + id));
	}

	@Override
	public void deleteById(long id) {
		Notice notice = findById(id);
		noticeRepository.delete(notice);
	}

	@Override
	public List<Notice> findAll() {
		return noticeRepository.findAll();
	}

	
}
