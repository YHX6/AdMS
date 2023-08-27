package com.jgchuanmei.admng.service;

import com.jgchuanmei.admng.dao.SpotRepository;
import com.jgchuanmei.admng.entity.Spot;
import com.jgchuanmei.admng.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpotServiceImpl implements SpotService {
    private SpotRepository spotRepository;

    @Autowired
    public SpotServiceImpl(SpotRepository spotRepository){
        this.spotRepository = spotRepository;
    }

    @Override
    public List<Spot> findAll() {
        return spotRepository.findAll();
    }

    @Override
    public Spot save(Spot spot) {
        return spotRepository.save(spot);
    }

    @Override
    public void deleteById(Integer id) {
        spotRepository.deleteById(id);
    }

    @Override
    public Optional<Spot> findById(Integer id) {
        return spotRepository.findById(id);
    }

    @Override
    public List<Spot> getAllByQuery(String id, String type, String station, String spotNumber, String train, String status) {
        return spotRepository.getAllByQuery(id, type, station, spotNumber, train, status);
    }

    @Override
    public Page<Spot> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);


        return spotRepository.findAll(pageable);
    }

    @Override
    public APIResponse<List<Spot>> findPaginatedByQuery(String id, String type, String station, String spotNumber, String train, String status, int pageSize, int pageNo) {
        return spotRepository.getAllByQuery(id, type, station, spotNumber, train, status, pageSize, pageNo);
    }

    @Override
    public int updateSpotStatus(Integer id, String status) {
        return spotRepository.updateSpotStatus(id, status);
    }

    @Override
    public Spot findSpot(String type, String station, String spotNumber, String train) {
        return spotRepository.findSpot(type,station, spotNumber, train);
    }

    @Override
    public void updateStatusById(String status, Integer id) {
        spotRepository.updateStatusById(status, id);
    }


}
