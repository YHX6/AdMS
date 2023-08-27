package com.jgchuanmei.admng.controller;

import com.jgchuanmei.admng.entity.BarChartPair;
import com.jgchuanmei.admng.service.ChartService;
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
//        List<Double> data = chartService.getPieData();
//        System.out.println(data.toString());

        return  chartService.getPieData();
    }

    @RequestMapping("/charts/bar")
    public List<List<Object>> getBarData(){
//        List<List<BarChartPair>> result = chartService.getBarData();
//
//        List<List<Object>> data = new ArrayList<>();
//        List<Object> labels = new ArrayList<>();
//        List<Object> adCounts = new ArrayList<>();
//        List<Object> spotCounts = new ArrayList<>();
//
//        // get all labels
//        for(BarChartPair p:result.get(1)){
//            labels.add(p.getStation());
//            spotCounts.add(p.getCount());
//        }
//
//        for(Object label:labels){
//            // get count or default 0
//            boolean has = false;
//            for(BarChartPair p:result.get(0)){
//                if(p.getStation().equals((String) label)){
//                    adCounts.add(p.getCount());
//                    has = true;
//                    break;
//                }
//            }
//            if(!has) adCounts.add(0);
//        }
//
//        data.add(labels);
//        data.add(adCounts);
//        data.add(spotCounts);
//
//        System.out.println(data.toString());
//        System.out.println(result.toString());

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
