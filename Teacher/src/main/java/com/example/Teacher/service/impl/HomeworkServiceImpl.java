package com.example.Teacher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Teacher.dao.HomeworkRepository;
import com.example.Teacher.entities.Homework;
import com.example.Teacher.exception.HomeworkNotFoundException;
import com.example.Teacher.service.HomeworkService;

@Service
public class HomeworkServiceImpl implements HomeworkService{
	
	@Autowired
	HomeworkRepository homeworkRepository;

	@Override
	public Homework save(Homework homework) {
		return homeworkRepository.save(homework);
	}

	@Override
	public List<Homework> findAll() {
		return homeworkRepository.findAll();
	}

	@Override
	public Homework findById(long id) {
		return homeworkRepository.findById(id).orElseThrow(()->new HomeworkNotFoundException("Homework not found with id: " + id));
	}

	@Override
	public Homework updateHomeworkById(long id, Homework homework) {
		Homework oldHomework = findById(id);
		oldHomework.setTitle(homework.getTitle());
		oldHomework.setDescription(homework.getDescription());
		oldHomework.setToWhichStandard(homework.getToWhichStandard());
		oldHomework.setDueDate(homework.getDueDate());
		return homeworkRepository.save(oldHomework);
	}

	@Override
	public void deleteById(long id) {
		Homework homework = findById(id);
		homeworkRepository.delete(homework);
		
	}

	@Override
	public List<Homework> findByToWhichStandard(String standard) {
		List<Homework>homeworkList =  homeworkRepository.findByToWhichStandard(standard);
		return homeworkList; 
	}

}
