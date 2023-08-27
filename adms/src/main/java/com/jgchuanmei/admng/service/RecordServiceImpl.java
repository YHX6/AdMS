package com.jgchuanmei.admng.service;

import com.jgchuanmei.admng.dao.RecordRepository;
import com.jgchuanmei.admng.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    private RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public List<Record> findAllByOrderByTimeDesc() {
        return recordRepository.findAllByOrderByTimeDesc();
    }

    @Override
    public Page<Record> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, Sort.by("time").descending());

        return recordRepository.findAll(pageable);
    }

    @Override
    public void save(Record record) {
        recordRepository.save(record);
    }
}
