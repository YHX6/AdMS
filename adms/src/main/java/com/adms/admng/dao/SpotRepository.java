package com.adms.admng.dao;

import com.adms.admng.entity.Spot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpotRepository extends JpaRepository<Spot, Integer>, SpotRepositoryCustom {
    @Query("UPDATE Spot s SET s.status = ?1 WHERE s.id = ?2")
    public void updateStatusById(String status, Integer id);
}
