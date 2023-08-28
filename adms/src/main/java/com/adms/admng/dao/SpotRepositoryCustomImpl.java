package com.adms.admng.dao;

import com.adms.admng.entity.BarChartPair;
import com.adms.admng.entity.Spot;
import com.adms.admng.util.APIResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


public class SpotRepositoryCustomImpl implements SpotRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Spot> getAllByQuery(String id, String type, String station, String spotNumber, String train, String status) {
        String sql = "select id, type, station, spot_number, train, status, remark from spot where 1=1 ";

        HashMap<String, String> map = new HashMap<>();
        if(!isEmpty(id)) map.put("id", id);
        if(!isEmpty(type)) map.put("type", type);
        if(!isEmpty(station)) map.put("station", station);
        if(!isEmpty(spotNumber)) map.put("spot_number", spotNumber);
        if(!isEmpty(train)) map.put("train", train);
        if(!isEmpty(status)) map.put("status", status);

        for(String key:map.keySet()){
            sql += "and "+ key +" = '" + map.get(key) + "' ";
        }

        Query query = entityManager.createNativeQuery(sql, Spot.class);

        return query.getResultList();
    }

    @Override
    public APIResponse<List<Spot>> getAllByQuery(String id, String type, String station, String spotNumber, String train, String status, Integer pageSize, Integer pageNo) {
        String sql = "select id, type, station, spot_number, train, status, remark from spot where 1=1 ";
        String sql2 = "select count(*) from spot where 1=1 ";

        HashMap<String, String> map = new HashMap<>();
        if(!isEmpty(id)) map.put("id", id);
        if(!isEmpty(type)) map.put("type", type);
        if(!isEmpty(station)) map.put("station", station);
        if(!isEmpty(spotNumber)) map.put("spot_number", spotNumber);
        if(!isEmpty(train)) map.put("train", train);
        if(!isEmpty(status)) map.put("status", status);

        for(String key:map.keySet()){
            sql += "and "+ key +" = '" + map.get(key) + "' ";
            sql2 += "and "+ key +" = '" + map.get(key) + "' ";
        }
        int offset = pageSize*pageNo - pageSize;
        sql += "limit " + offset +"," + pageSize;

        // get API
        Integer totalItems = (Integer) entityManager.createNativeQuery(sql2, Integer.class).getResultList().get(0);
        Integer totalPages =  (totalItems+ pageSize-1)/pageSize;
        List<Spot> spots =   entityManager.createNativeQuery(sql, Spot.class).getResultList();



        return new APIResponse<List<Spot>>(spots, pageNo, totalPages, totalItems);
    }

    @Override
    @Transactional
    public int updateSpotStatus(Integer id, String status) {
        return  entityManager
                .createNativeQuery("update spot set status=? where id=?")
                .setParameter(1, status)
                .setParameter(2, id)
                .executeUpdate();
    }

    @Override
    public Spot findSpot(String type, String station, String spotNumber, String train) {
        Query query = entityManager.createNativeQuery("select id, type, station, spot_number, train, status, remark from spot " +
                "where type=? and station=? and spot_number=? and train=? ", Spot.class);

        List<Spot> rs = query
                .setParameter(1, type)
                .setParameter(2, station)
                .setParameter(3, spotNumber)
                .setParameter(4, train)
                .getResultList();

        if(rs.size() == 0) return null;
        return rs.get(0);
    }

    @Override
    public List<Double> getPieData() {
        Query query = entityManager.createNativeQuery("select count(*) from spot where status=?", Integer.class);

        int d1 = (Integer) query.setParameter(1, "Occupied").getResultList().get(0);
        int d2 = (Integer) query.setParameter(1, "Available").getResultList().get(0);
        int d3 = (Integer) query.setParameter(1, "Other").getResultList().get(0);
        double total = 0.0 + d1 + d2 + d3;


        return Arrays.asList(100*d1/total, 100*d2/total, 100*d3/total);
    }

    @Override
    public List<BarChartPair> getBarData() {
        Query query = entityManager.createNativeQuery("select coalesce(countad,0) countad, countspot, a.station from \n" +
                "(select count(*) as countspot, station from spot where station!= '/' group by station) as a \n" +
                "\n" +
                "left join\n" +
                "(select count(*) as countad,  spot.station as station from spot, ad  where spot.train ='/' and ad.spot_id = spot.id and ad.isdroped = 0 group by station)\n" +
                " as b\n" +
                "on a.station = b.station;", BarChartPair.class);

        return query.getResultList();
    }

    private boolean isEmpty(String str){
        return str == null || str.equals("");
    }
}
