package com.adms.admng.service;

import com.adms.admng.dao.AjaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AjaxServiceImpl implements AjaxService {

    private AjaxRepository ajaxRepository;

    @Autowired
    public AjaxServiceImpl(AjaxRepository ajaxRepository) {
        this.ajaxRepository = ajaxRepository;
    }

    @Override
    public List<String> findAllStations() {
        return ajaxRepository.findAllStations();
    }

    @Override
    public List<String> findAllStationsIncludingTrain() {
        return ajaxRepository.findAllStationsIncludingTrain();
    }

    @Override
    public List<String> findAllSpotNumbers(String station) {
        return ajaxRepository.findAllSpotNumbers(station);
    }

    @Override
    public List<String> findAllSpotNumbers() {
        return ajaxRepository.findAllSpotNumbers();
    }

    @Override
    public List<String> findAllAdTypes() {
        return ajaxRepository.findAllAdTypes();
    }

    @Override
    public List<String> findAllAdIndustries() {
        return ajaxRepository.findAllAdIndustries();
    }

    @Override
    public List<String> findAllTrains() {
        return ajaxRepository.findAllTrains();
    }

    @Override
    public List<String> findAllTrainsIncludingStation() {
        return ajaxRepository.findAllTrainsIncludingStation();
    }
}
