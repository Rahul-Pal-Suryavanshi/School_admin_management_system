package com.example.Notice.service;

import java.util.List;

import com.example.Notice.dto.NoticeDetails;
import com.example.Notice.entities.Notice;

public interface NoticeService {
	
	Notice save(NoticeDetails noticeDetails);
	Notice findById(long id);
	
	List<Notice> findAll();
	
	void deleteById(long id);
	
	//CRUD operations
}
