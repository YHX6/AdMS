package com.adms.admng.service;

import java.util.List;

public interface AjaxService {
    List<String> findAllStations();

    List<String> findAllStationsIncludingTrain();

    List<String> findAllSpotNumbers(String station);

    List<String> findAllTrains();

    List<String> findAllTrainsIncludingStation();

    List<String> findAllSpotNumbers();

    List<String> findAllAdTypes();

    List<String> findAllAdIndustries();
}
