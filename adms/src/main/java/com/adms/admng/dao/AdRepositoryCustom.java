package com.adms.admng.dao;

import com.adms.admng.entity.Ad;
import com.adms.admng.util.APIResponse;

import java.util.List;

public interface AdRepositoryCustom {
    APIResponse<List<Ad>> getAdUnsettled(int pageSize, int pageNo);

    APIResponse<List<Ad>> getAll(int pageSize, int pageNo);

    int saveAd(Ad ad);

    int updateAd(Ad ad);

    APIResponse<List<Ad>> getAllByQuery(String id, String type, String station, String spotNumber,
                           String train, String adType, String industryType, String content,
                           String company, String ddl, String status,int pageSize, int pageNo);

    int dropAd(Integer id);

    Ad getAdById(Integer id);
}
