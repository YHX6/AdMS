package com.jgchuanmei.admng.service;

import com.jgchuanmei.admng.dao.SpotRepository;
import com.jgchuanmei.admng.entity.BarChartPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class ChartServiceImpl implements ChartService {

    private SpotRepository spotRepository;

    @Autowired
    public ChartServiceImpl(SpotRepository spotRepository) {
        this.spotRepository = spotRepository;
    }

    @Override
    public List<Double> getPieData() {

        return spotRepository.getPieData();
    }

    @Override
    public List<BarChartPair> getBarData() {
        return spotRepository.getBarData();
    }

//    @Override
//    public List<List<BarChartPair>> getBarData() {
//        return Arrays.asList(spotRepository.getBarDataAd(), spotRepository.getBarDataSpot());
////        return spotRepository.getBarData();
//    }
}
