package com.adms.admng.dao;

import com.adms.admng.entity.Ad;
import com.adms.admng.util.APIResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class AdRepositoryImpl implements AdRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public APIResponse<List<Ad>> getAdUnsettled(int pageSize, int pageNo) {
        Integer totalItems = (Integer) entityManager.createNativeQuery(
                "select ad.id as id,  ad_type, industry_type, content,company, ddl, picture, ad.remark as remark, spot.id as spot_id, spot.type as spot_type, spot.station as spot_station, spot.spot_number as spot_number, spot.train as spot_train, ad.isdroped as isdroped " +
                        "from ad as ad, spot as spot where ad.spot_id = spot.id and not ad.isdroped order by ddl"
                , Ad.class).getResultList().size();
        Integer totalPages =  (totalItems + pageSize - 1)/pageSize;
        int offset = pageSize*pageNo - pageSize;

        List<Ad> ads = entityManager.createNativeQuery(
                "select ad.id as id,  ad_type, industry_type, content,company, ddl, picture, ad.remark as remark, spot.id as spot_id, spot.type as spot_type, spot.station as spot_station, spot.spot_number as spot_number, spot.train as spot_train, ad.isdroped as isdroped " +
                        "from ad as ad, spot as spot where ad.spot_id = spot.id and not ad.isdroped order by ddl limit " + offset + "," + pageSize
                , Ad.class).getResultList();



        return new APIResponse<List<Ad>>(ads, pageNo, totalPages, totalItems);
    }

    @Override
    public APIResponse<List<Ad>> getAll(int pageSize, int pageNo) {
        Integer totalItems = (Integer) entityManager.createNativeQuery(
                "select ad.id as id,  ad_type, industry_type, content,company, ddl, picture, ad.remark as remark, spot.id as spot_id, spot.type as spot_type, spot.station as spot_station, spot.spot_number as spot_number, spot.train as spot_train, ad.isdroped as isdroped " +
                        "from ad as ad, spot as spot where ad.spot_id = spot.id "
                , Ad.class).getResultList().size();
        Integer totalPages =  (totalItems + pageSize - 1)/pageSize;
        int offset = pageSize*pageNo - pageSize;

        List<Ad> ads = entityManager.createNativeQuery(
                "select ad.id as id,  ad_type, industry_type, content,company, ddl, picture, ad.remark as remark, spot.id as spot_id, spot.type as spot_type, spot.station as spot_station, spot.spot_number as spot_number, spot.train as spot_train, ad.isdroped as isdroped " +
                        "from ad as ad, spot as spot where ad.spot_id = spot.id limit " + offset + "," + pageSize
                , Ad.class).getResultList();



        return new APIResponse<List<Ad>>(ads, pageNo, totalPages, totalItems);
    }

    private boolean isValidString(String str){
        return str != null && !str.equals("");
    }


    @Override
    public APIResponse<List<Ad>> getAllByQuery(String id, String type, String station, String spotNumber,
                                  String train, String adType, String industryType, String content,
                                  String company, String ddl, String status, int pageSize, int pageNo) {
        String sql = "select ad.id as id,  ad_type, industry_type, content,company, ddl, picture, ad.remark as remark, spot.id as spot_id, spot.type as spot_type, spot.station as spot_station, spot.spot_number as spot_number, spot.train as spot_train, ad.isdroped as isdroped " +
                    "from ad as ad, spot as spot where ad.spot_id = spot.id ";

        // add search sql
        HashMap<String, String> map = new HashMap<>();
        if(isValidString(id)) map.put("ad.id", id);
        if(isValidString(type)) map.put("spot.type", type);
        if(isValidString(station)) map.put("spot.station", station);
        if(isValidString(spotNumber)) map.put("spot.spot_number", spotNumber);
        if(isValidString(train)) map.put("spot.train", train);
        if(isValidString(adType)) map.put("ad_type", adType);
        if(isValidString(industryType)) map.put("industry_type", industryType);
        if(isValidString(content)) map.put("content", content);
        if(isValidString(company)) map.put("company", company);
        for(String key: map.keySet()){
            sql += "and "+key +"=? ";
        }
        // add dll
        if(isValidString(ddl)){
            sql += "and ddl >='" + ddl + "-01' and ddl <= '" + ddl + "-31' ";
        }

        // add status
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String today = dtf.format(now);
        if(status.equals("Invalid")){
            sql += "and ad.isdroped=false and ddl <= '" + today +"' ";
        }else if(status.equals("Valid")){
            sql += "and ad.isdroped=false and ddl > '"+today +"' ";
        }else if(status.equals("Dropped")){
            sql += "and ad.isdroped=true ";
        }


        Query query = entityManager.createNativeQuery(sql, Ad.class);

        int count = 0;
        for(String key:map.keySet()){
            count ++;
            query.setParameter(count, map.get(key));
        }

        // get totalitems
        Integer totalItems = query.getResultList().size();
        Integer totalPages =  (totalItems + pageSize - 1)/pageSize;
        int offset = pageSize*pageNo - pageSize;

        // get ad list
        query = entityManager.createNativeQuery(sql + " limit " + offset + "," + pageSize, Ad.class);
        count = 0;
        for(String key:map.keySet()){
            count ++;
            query.setParameter(count, map.get(key));
        }

        return new APIResponse<List<Ad>>(query.getResultList(), pageNo, totalPages, totalItems);
    }

    @Override
    @Transactional
    public int dropAd(Integer id) {
        return  entityManager
                .createNativeQuery("update ad set isdroped=true where id=?")
                .setParameter(1, id)
                .executeUpdate();

    }

    @Override
    public Ad getAdById(Integer id) {
        Query query = entityManager.createNativeQuery(
                "select ad.id as id,  ad_type, industry_type, content,company, ddl, picture, ad.remark as remark, spot.id as spot_id, spot.type as spot_type, spot.station as spot_station, spot.spot_number as spot_number, spot.train as spot_train, ad.isdroped as isdroped " +
                        "from ad as ad, spot as spot where ad.spot_id = spot.id and ad.id = ?"
                , Ad.class).setParameter(1, id);


        return (Ad) query.getResultList().get(0);
    }


    @Override
    @Transactional
    public int saveAd(Ad ad) {
        int spotId = -1;
        if(ad.getSpotType().equals("In_station")){
            spotId = (Integer) entityManager.createNativeQuery(
                            "select id from spot where `type`=? and station=? and spot_number=?",
                            Integer.class
                    )
                    .setParameter(1,ad.getSpotType())
                    .setParameter(2, ad.getStation())
                    .setParameter(3, ad.getSpotNumber())
                    .getResultList().get(0);
        }else if(ad.getSpotType().equals("In_train")){
            spotId = (Integer)  entityManager.createNativeQuery(
                            "select id from spot where `type`=? and train=?",
                            Integer.class
                    )
                    .setParameter(1,ad.getSpotType())
                    .setParameter(2, ad.getTrain())
                    .getResultList().get(0);
        }

        // set status of spot


        int result = entityManager.createNativeQuery(
                "insert into ad (`spot_id`,`ad_type`,`industry_type`, `content`,`company`, `ddl`,`picture`,`remark`, `isdroped`) values" +
                        "(?, ?,?,?, ?,?,?, ?, ?)"
        ).setParameter(1, spotId)
                .setParameter(2,ad.getAdType())
                .setParameter(3,ad.getIndustryType())
                .setParameter(4,ad.getContent())
                .setParameter(5,ad.getCompany())
                .setParameter(6,ad.getDdl())
                .setParameter(7,ad.getPicture())
                .setParameter(8, ad.getRemark())
                .setParameter(9, ad.getIsdroped())
                .executeUpdate();
        return result;

    }

    @Override
    @Transactional
    public int updateAd(Ad ad) {
        int spotId = -1;
        if(ad.getSpotType().equals("In_station")){
            spotId = (Integer) entityManager.createNativeQuery(
                            "select id from spot where `type`=? and station=? and spot_number=?",
                            Integer.class
                    )
                    .setParameter(1,ad.getSpotType())
                    .setParameter(2, ad.getStation())
                    .setParameter(3, ad.getSpotNumber())
                    .getResultList().get(0);
        }else if(ad.getSpotType().equals("In_train")){
            spotId = (Integer)  entityManager.createNativeQuery(
                            "select id from spot where `type`=? and train=?",
                            Integer.class
                    )
                    .setParameter(1,ad.getSpotType())
                    .setParameter(2, ad.getTrain())
                    .getResultList().get(0);
        }


        int result = entityManager.createNativeQuery(

                        "update ad set `spot_id`=?,`ad_type`=?,`industry_type`=?, `content`=?,`company`=?, `ddl`=?,`picture`=?,`remark`=?, `isdroped`=? where id = ?"
                )
                .setParameter(1, spotId)
                .setParameter(2,ad.getAdType())
                .setParameter(3,ad.getIndustryType())
                .setParameter(4,ad.getContent())
                .setParameter(5,ad.getCompany())
                .setParameter(6,ad.getDdl())
                .setParameter(7,ad.getPicture())
                .setParameter(8, ad.getRemark())
                .setParameter(9, ad.getIsdroped())
                .setParameter(10, ad.getId())
                .executeUpdate();
        return result;
    }
}
