package com.adms.admng.service;

import com.adms.admng.dao.AdRepository;
import com.adms.admng.entity.Ad;
import com.adms.admng.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    private AdRepository adRepository;

    @Autowired
    public AdServiceImpl(AdRepository adRepository){
        this.adRepository = adRepository;
    }


    @Override
    public APIResponse<List<Ad>> getAdUnsettled(int pageSize, int pageNo) {
        return adRepository.getAdUnsettled(pageSize,pageNo);
    }

    @Override
    public APIResponse<List<Ad>> getAll(int pageSize, int pageNo) {
        return adRepository.getAll(pageSize,pageNo);
    }

    @Override
    public int saveAd(Ad ad) {
        return adRepository.saveAd(ad);
    }

    @Override
    public int updateAd(Ad ad) {
        return adRepository.updateAd(ad);
    }

    @Override
    public APIResponse<List<Ad>> getAllByQuery(String id, String type, String station, String spotNumber,
                                  String train, String adType, String industryType, String content,
                                  String company, String ddl, String status,int pageSize, int pageNo) {
        return adRepository.getAllByQuery(id, type, station, spotNumber, train, adType, industryType,content,company,ddl, status,pageSize,pageNo);
    }

    @Override
    public int dropAd(Integer id) {
        return adRepository.dropAd(id);
    }

    @Override
    public Ad getAdById(Integer id) {
        return adRepository.getAdById(id);
    }
}
