package com.adms.admng.dao;

import java.util.List;

public interface AjaxRepository {
    List<String> findAllStations();

    List<String> findAllStationsIncludingTrain();

    List<String> findAllSpotNumbers(String station);

    List<String> findAllSpotNumbers();

    List<String> findAllTrains();

    List<String> findAllTrainsIncludingStation();

    List<String> findAllAdTypes();

    List<String> findAllAdIndustries();
}
