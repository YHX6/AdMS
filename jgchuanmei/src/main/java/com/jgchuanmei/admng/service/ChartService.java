package com.jgchuanmei.admng.service;

import com.jgchuanmei.admng.entity.BarChartPair;

import java.util.List;

public interface ChartService {

    List<Double> getPieData();

//    List<List<BarChartPair>> getBarData();
    List<BarChartPair> getBarData();
}
