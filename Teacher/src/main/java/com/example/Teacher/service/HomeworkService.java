package com.example.Teacher.service;

import java.util.List;

import com.example.Teacher.entities.Homework;



public interface HomeworkService {
	Homework save(Homework homework);
	List<Homework> findAll();
	Homework findById(long id);
	Homework updateHomeworkById(long id,Homework homework);
	void deleteById(long id);
	List<Homework> findByToWhichStandard(String standard);
}
