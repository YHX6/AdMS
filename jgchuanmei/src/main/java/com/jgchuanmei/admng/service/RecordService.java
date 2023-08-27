package com.jgchuanmei.admng.service;

import com.jgchuanmei.admng.entity.Record;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RecordService {
    List<Record> findAllByOrderByTimeDesc();

    Page<Record> findPaginated(int pageNo, int pageSize);

    void save(Record record);
}
