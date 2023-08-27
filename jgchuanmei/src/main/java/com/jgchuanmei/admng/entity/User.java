package com.jgchuanmei.admng.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
@Entity
public class User{
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)   //this is for int id
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="roles")
    private String roles;

    @Column(name="account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "failed_attempt")
    private int failedAttempt;

    @Column(name = "lock_time")
    private Date lockTime;

    @Transient
    public String getTopRole(){
        if(roles == null || roles.equals("")){
            return null;
        }else if(roles.contains("ROLE_ADMIN")){
            return "超级号";
        }else if(roles.contains("ROLE_MANAGER")){
            return "管理员";
        }else{
            return "员工";
        }
    }

    @Transient
    public String topRole;


    public void formatRoles(){
        if(topRole == null || topRole.equals("")){
            roles = null;
        }else if(topRole.equals("超级号")){
            roles =  "ROLE_STAFF,ROLE_MANAGER,ROLE_ADMIN";
        }else if(topRole.equals("管理员")){
            roles =  "ROLE_STAFF,ROLE_MANAGER";
        }else{
            roles = "ROLE_STAFF";
        }
    }

}
