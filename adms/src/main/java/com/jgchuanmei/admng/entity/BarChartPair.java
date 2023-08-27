package com.jgchuanmei.admng.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarChartPair {

    @Column(name="countad")
    Integer countad;

    @Column(name="countspot")
    Integer countspot;
    @Id
    @Column(name="station")
    private String station;
}
