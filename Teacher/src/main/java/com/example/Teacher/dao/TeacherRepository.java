package com.example.Teacher.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Teacher.entities.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

}
