package com.example.student.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	List<Student> findByStandard(String standard);
}
