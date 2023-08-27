package com.jgchuanmei.admng.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
@Table(name = "ad")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ToString(exclude = "spot")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "spot_id")
//    private Spot spot;

    @Column(name = "spot_id")
    private Integer spotId;


    @Column(name="ad_type")
    private String adType;
    @Column(name="industry_type")
    private String industryType;
    @Column(name="content")
    private String content;
    @Column(name="company")
    private String company;
    @Column(name="ddl")
    private String ddl;
    @Column(name="picture")
    private String picture;
    @Column(name="remark")
    private String remark;

    //Spot info
    @Column(name = "spot_type")
    private String spotType;
    @Column(name = "spot_station")
    private String station;
    @Column(name = "spot_number")
    private String spotNumber;
    @Column(name = "spot_train")
    private String train;


    @Column(name="isdroped")
    private Boolean isdroped;


    // other
    @Transient  // used to ignore the field for ORM
    public String getPictureDirectory(){
        //TBD: set a default not found photo
        if(picture == null ) return null;

        return "/ad-photos/" + id + "/" + picture;
    }

    @Transient  // used to ignore the field for ORM
    public String getStatus(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(cal.getTime());

        if(isdroped){
            return "已下刊";
        }else if(ddl.compareTo(today) <= 0){
            return "已过期";
        }else{
            return "未过期";
        }
    }

    @Transient  // used to ignore the field for ORM
    public boolean lessThanSeven(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String weekLater = sdf.format(cal.getTime());

        return ddl.compareTo(weekLater) <= 0;
    }
}
