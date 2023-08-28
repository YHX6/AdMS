package com.adms.admng.dao;

import com.adms.admng.entity.BarChartPair;
import com.adms.admng.entity.Spot;
import com.adms.admng.util.APIResponse;

import java.util.List;
import java.util.Optional;

public interface SpotRepositoryCustom {
    List<Spot> getAllByQuery(String id, String type, String station, String spotNumber, String train, String status);

    APIResponse<List<Spot>> getAllByQuery(String id, String type, String station, String spotNumber, String train, String status, Integer pageSize, Integer pageNo);

    int updateSpotStatus(Integer id, String status);

    Spot findSpot(String type, String station, String spotNumber, String train);

    List<Double> getPieData();

//    List<List<BarChartPair>> getBarData();
//    List<BarChartPair> getBarDataSpot();
//    List<BarChartPair> getBarDataAd();
    List<BarChartPair> getBarData();
}
