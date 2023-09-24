package com.adms.admng.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AjaxRepositoryImpl implements AjaxRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<String> findAllStations() {
        Query query = entityManager.createNativeQuery(
                "select distinct station from spot where type='In_station'"
                , String.class);

        return query.getResultList();
    }

    @Override
    public List<String> findAllStationsIncludingTrain() {
        Query query = entityManager.createNativeQuery(
                "select distinct station from spot "
                , String.class);

        System.out.println(query.getResultList());

        return query.getResultList();
    }


    @Override
    public List<String> findAllSpotNumbers(String station) {
        Query query = entityManager.createNativeQuery(
                "select distinct spot_number from spot where station = ?"
                , String.class);
        query.setParameter(1, station);
        return query.getResultList();
    }


    @Override
    public List<String> findAllSpotNumbers() {
        Query query = entityManager.createNativeQuery(
                "select distinct spot_number from spot "
                , String.class);
        return query.getResultList();
    }


    @Override
    public List<String> findAllTrains() {
        Query query = entityManager.createNativeQuery(
                "select distinct train from spot where type='In_train'"
                , String.class);
        return query.getResultList();
    }

    @Override
    public List<String> findAllTrainsIncludingStation() {
        Query query = entityManager.createNativeQuery(
                "select distinct train from spot "
                , String.class);
        return query.getResultList();
    }

    @Override
    public List<String> findAllAdTypes() {
        Query query = entityManager.createNativeQuery(
                "select distinct ad_type from ad "
                , String.class);

        return query.getResultList();
    }

    @Override
    public List<String> findAllAdIndustries() {
        Query query = entityManager.createNativeQuery(
                "select distinct industry_type from ad "
                , String.class);

        return query.getResultList();
    }
}
