package com.example.studystream.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studystream.entities.StudyMaterial;

public interface StudyMaterialRepository extends JpaRepository<StudyMaterial, Long>{

	
}
