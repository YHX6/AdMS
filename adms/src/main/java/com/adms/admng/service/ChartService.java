package com.adms.admng.service;

import com.adms.admng.entity.BarChartPair;

import java.util.List;

public interface ChartService {

    List<Double> getPieData();

//    List<List<BarChartPair>> getBarData();
    List<BarChartPair> getBarData();
}
