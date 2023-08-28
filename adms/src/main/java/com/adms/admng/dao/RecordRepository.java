package com.adms.admng.dao;

import com.adms.admng.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {
    List<Record> findAllByOrderByTimeDesc();
}
