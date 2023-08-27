package com.jgchuanmei.admng.dao;

import com.jgchuanmei.admng.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUsername(String username);

    @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.username = ?2")
    @Modifying
    public void updateFailedAttempts(int failAttempts, String username);

    @Query("select count(p) = 1 from User p where p.username = ?1")
    public boolean findExistByUsername(String username);

}
