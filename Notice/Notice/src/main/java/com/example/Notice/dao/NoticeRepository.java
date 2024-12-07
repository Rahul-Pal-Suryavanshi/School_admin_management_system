package com.example.Notice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Notice.entities.Notice;

public interface NoticeRepository extends JpaRepository<Notice,Long> {

}
