package com.example.Teacher.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Teacher.entities.Homework;

public interface HomeworkRepository extends JpaRepository<Homework,Long> {
	List<Homework> findByToWhichStandard(String standard);
}
