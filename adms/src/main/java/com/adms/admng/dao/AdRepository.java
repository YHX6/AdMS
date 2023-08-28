package com.adms.admng.dao;

import com.adms.admng.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer>, AdRepositoryCustom {
}
