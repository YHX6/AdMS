package com.jgchuanmei.admng.dao;

import com.jgchuanmei.admng.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad, Integer>, AdRepositoryCustom {
}
