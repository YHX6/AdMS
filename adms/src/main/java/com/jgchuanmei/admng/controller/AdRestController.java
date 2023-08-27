package com.jgchuanmei.admng.controller;

import com.jgchuanmei.admng.entity.Ad;
import com.jgchuanmei.admng.entity.Spot;
import com.jgchuanmei.admng.service.AdService;
import com.jgchuanmei.admng.service.SpotService;
import com.jgchuanmei.admng.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdRestController {

    private AdService adService;
    private SpotService spotService;

    @Autowired
    public AdRestController(AdService adService, SpotService spotService) {
        this.adService = adService;
        this.spotService = spotService;
    }


    @GetMapping("/ads-unsettled/page/{pageNo}")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public APIResponse<List<Ad>> getAdUnsettled(@PathVariable(value = "pageNo") int pageNo){


        return adService.getAdUnsettled(10, pageNo);
    }

    @GetMapping("/ads-query/page/{pageNo}")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public APIResponse<List<Ad>> getAllByQuery(@PathVariable(value = "pageNo") int pageNo, String id, String type, String station, String spotNumber,
                                  String train, String adType, String industryType, String content,
                                  String company, String ddl, String status){
        return adService.getAllByQuery(id, type, station, spotNumber, train, adType, industryType,content,company,ddl, status, 10, pageNo);
    }

}
