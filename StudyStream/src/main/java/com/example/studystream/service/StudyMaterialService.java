package com.example.studystream.service;

import java.util.List;

import com.example.studystream.entities.StudyMaterial;



public interface StudyMaterialService {
	StudyMaterial save(StudyMaterial studyMaterial);
	StudyMaterial findById(long id);
	List<StudyMaterial> findAll();
	void delete(long id);
}
