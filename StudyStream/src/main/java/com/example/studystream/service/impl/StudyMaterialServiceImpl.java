package com.example.studystream.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studystream.dao.StudyMaterialRepository;
import com.example.studystream.entities.StudyMaterial;
import com.example.studystream.exception.StudyMaterialNotFoundException;
import com.example.studystream.service.StudyMaterialService;

@Service
public class StudyMaterialServiceImpl implements StudyMaterialService{
	@Autowired
	StudyMaterialRepository studyMaterialRepository;

	@Override
	public StudyMaterial save(StudyMaterial studyMaterial) {
		return studyMaterialRepository.save(studyMaterial);
	}

	@Override
	public StudyMaterial findById(long id) {
		return studyMaterialRepository.findById(id).orElseThrow(()->new StudyMaterialNotFoundException("StudyMaterial not found with id: " + id));
	}

	@Override
	public List<StudyMaterial> findAll() {
		return studyMaterialRepository.findAll();
	}

	@Override
	public void delete(long id) {
		StudyMaterial studyMaterial = findById(id);
		studyMaterialRepository.delete(studyMaterial);
	}
	
}
