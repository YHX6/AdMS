package com.adms.admng.controller;

import com.adms.admng.entity.Record;
import com.adms.admng.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RecordController {

    private RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/records")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getRecords(Model theModel){
//        List<Record> records = recordService.findAllByOrderByTimeDesc();
//        theModel.addAttribute("records", records);
//        return "records-all";
        return getRecordsPagination(1, theModel);
    }

    @GetMapping("/records/page/{pageNo}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getRecordsPagination(@PathVariable(value = "pageNo") int pageNo, Model theModel){
        int pageSize = 10;
        Page<Record> page = recordService.findPaginated(pageNo, pageSize);
        List<Record> records = page.getContent();

        theModel.addAttribute("currentPage", pageNo);
        theModel.addAttribute("totalPages", page.getTotalPages());
        theModel.addAttribute("totalItems", page.getTotalElements());
        theModel.addAttribute("records", records);
        return "records-all";
    }
}
