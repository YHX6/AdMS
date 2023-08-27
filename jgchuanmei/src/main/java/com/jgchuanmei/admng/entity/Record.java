package com.jgchuanmei.admng.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="user_id")
    private String userId;
    @Column(name="username")
    private String username;
    @Column(name="ip")
    private String ip;

    @Column(name="uri")
    private String uri;
    @Column(name="operation")
    private String operation;
    @Column(name="time")
    private Date time;


    public Record(String userId, String username, String ip, String uri, String operation, Date time) {
        this.userId = userId;
        this.username = username;
        this.ip = ip;
        this.uri = uri;
        this.operation = operation;
        this.time = time;
    }
}
