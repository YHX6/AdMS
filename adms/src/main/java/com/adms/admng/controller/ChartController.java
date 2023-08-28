package com.adms.admng.controller;

import com.adms.admng.entity.BarChartPair;
import com.adms.admng.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChartController {

    private ChartService chartService;

    @Autowired
    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    @RequestMapping("/charts/pie")
    public List<Double> getPieData(){
        return  chartService.getPieData();
    }

    @RequestMapping("/charts/bar")
    public List<List<Object>> getBarData(){

        List<BarChartPair> list = chartService.getBarData();

        List<List<Object>> data = new ArrayList<>();
        List<Object> labels = new ArrayList<>();
        List<Object> adCounts = new ArrayList<>();
        List<Object> spotCounts = new ArrayList<>();

        for(BarChartPair p:list){
            labels.add(p.getStation());
            adCounts.add(p.getCountad());
            spotCounts.add(p.getCountspot());
        }

        data.add(labels);
        data.add(adCounts);
        data.add(spotCounts);

        return data;
    }
}
