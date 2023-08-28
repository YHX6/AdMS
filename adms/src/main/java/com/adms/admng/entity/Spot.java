package com.adms.admng.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "spot")
//@ToString(exclude = "ads")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;
    @Column(name = "station")
    private String station;

    @Column(name = "spot_number")
    private String spotNumber;
    @Column(name = "train")
    private String train;
    @Column(name = "status")
    private String status;
    @Column(name = "remark")
    private String remark;


}
