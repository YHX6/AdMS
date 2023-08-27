package com.jgchuanmei.admng.service;

import com.jgchuanmei.admng.entity.Spot;
import com.jgchuanmei.admng.util.APIResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface SpotService {
    List<Spot> findAll();

    Spot save(Spot spot);

    void deleteById(Integer id);

    Optional<Spot> findById(Integer id);

    List<Spot> getAllByQuery(String id, String type, String station, String spotNumber, String train, String status);

    Page<Spot> findPaginated(int pageNo, int pageSize);

    APIResponse<List<Spot>> findPaginatedByQuery(String id, String type, String station, String spotNumber, String train, String status, int pageSize, int pageNo);

    int updateSpotStatus(Integer id, String status);

    Spot findSpot(String type, String station, String spotNumber, String train);

    public void updateStatusById(String status, Integer id);
}
