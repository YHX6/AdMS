package com.jgchuanmei.admng.controller;

import com.jgchuanmei.admng.entity.Spot;
import com.jgchuanmei.admng.service.SpotService;
import com.jgchuanmei.admng.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpotRestController {

    private SpotService spotService;

    @Autowired
    public SpotRestController(SpotService spotService) {
        this.spotService = spotService;
    }

//    @GetMapping("/spots-query")
//    public List<Spot> getAllByQuery(String id, String type, String station, String spotNumber, String train, String status){
//        return spotService.getAllByQuery(id, type, station, spotNumber, train, status);
//    }
    @GetMapping("/spots-query/page/{pageNo}")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public APIResponse<List<Spot>> getAllByQuery(@PathVariable(value="pageNo") int pageNo,String id, String type, String station, String spotNumber, String train, String status){
        int pageSize = 10;

        return spotService.findPaginatedByQuery(id, type, station, spotNumber, train, status, 10, pageNo);
    }
}
