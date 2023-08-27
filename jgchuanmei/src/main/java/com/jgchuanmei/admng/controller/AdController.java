package com.jgchuanmei.admng.controller;

import com.jgchuanmei.admng.entity.Ad;
import com.jgchuanmei.admng.entity.Record;
import com.jgchuanmei.admng.entity.Spot;
import com.jgchuanmei.admng.security.UserInfoUserDetails;
import com.jgchuanmei.admng.service.AdService;
import com.jgchuanmei.admng.service.RecordService;
import com.jgchuanmei.admng.service.SpotService;
import com.jgchuanmei.admng.util.APIResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class AdController {
    private AdService adService;
    private SpotService spotService;

    private RecordService recordService;

    public static String uploadDirectory = System.getProperty("user.dir") + "/img";

    @Autowired
    public AdController(AdService adService, SpotService spotService, RecordService recordService) {
        this.adService = adService;
        this.spotService = spotService;
        this.recordService = recordService;
    }

//    1.dashboard

    @RequestMapping("/")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public String welcomePage(Model theModel){
        return getAdsAll(1, theModel);
    }

    @RequestMapping("/ads")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public String getDashBoard(Model theModel){
        return getAdsAll(1, theModel);
    }


    @RequestMapping("/ads/page/{pageNo}")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public String getAdsAll(@PathVariable(value = "pageNo") int pageNo, Model theModel){
        int pageSize = 10;
        APIResponse<List<Ad>> res = adService.getAdUnsettled(pageSize, pageNo);
        theModel.addAttribute("ads", res.getResponse());
        theModel.addAttribute("currentPage", pageNo);
        theModel.addAttribute("totalPages", res.getTotalPages());
        theModel.addAttribute("totalItems", res.getTotalItems());

        return "ad-dashboard";
    }

//   2. add form

    @RequestMapping("/ads-form")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String getForm(Model theModel){
        Ad ad = new Ad();
        theModel.addAttribute("ad", ad);
        return "ad-form";
    }

//    @PostMapping("/ads-form/submit")
//    public String saveAd(@ModelAttribute("ad") Ad ad, Model theModel){
//        adService.saveAd(ad);
//        return getAdsShangaiPage(1, theModel);
//    }

//    @PostMapping("/ads-form/submit")
//    public String saveAd(@RequestParam("ad-form-type") String type,
//            @RequestParam("ad-form-picture") MultipartFile file,
//                         Model theModel){
////        adService.saveAd(ad);
//        System.out.println(type);
//        System.out.println(file.getOriginalFilename());
//        return getAdsShangaiPage(1, theModel);
//    }


    @PostMapping("/ads-form/submit")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String saveAd(@ModelAttribute("ad") Ad theAd, @RequestParam("ad-form-picture") MultipartFile file, Model theModel, HttpServletRequest request){
        //change spot status
        Spot spot;
        if(theAd.getSpotType().equals("站内")){
            spot = spotService.findSpot(theAd.getSpotType(), theAd.getStation(), theAd.getSpotNumber(), "/");
        }else{
            spot = spotService.findSpot(theAd.getSpotType(), "/", "/", theAd.getTrain());
        }

        // if it is occupied, change the status
        if(!theAd.getIsdroped()){
            if(spot == null){
                Ad ad = new Ad();
                theModel.addAttribute("ad", ad);
                theModel.addAttribute("error", "广告点位不存在！");
                return "ad-form";
            }else if(spot.getStatus().equals("已占用")){
                Ad ad = new Ad();
                theModel.addAttribute("ad", ad);
                theModel.addAttribute("error", "广告点位已被占用！");
                return "ad-form";
            }
            spotService.updateSpotStatus(spot.getId(), "已占用");
        }




        // upload file
        if(file.isEmpty()){
            theAd.setPicture("default/not-found.png");
        }else{
            String fileName = RandomStringUtils.randomAlphabetic(20) + "_" +file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory , fileName).toString();
            theAd.setPicture(fileName);


            try{
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            }catch (Exception e){
                e.printStackTrace();
                theModel.addAttribute("error", e.toString());
                return "error";
            }
        }



        // create new record
        UserInfoUserDetails userDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recordService.save(
                new Record(
                        userDetails.getUser().getId(),
                        userDetails.getUsername(),
                        request.getRemoteAddr(),
                        request.getRequestURI(),
                        "广告上刊（创建） 新广告点位ID: " + spot.getId(),
                        new Date()));

        adService.saveAd(theAd);
        return getAdsShangaiPage(1, theModel);
    }


    // 3. update form
    @RequestMapping("/ads-update")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String getFormUpdate(@RequestParam("id") Integer id, Model theModel){
//        Ad ad = adService.getAdById(id);
        theModel.addAttribute("id", id);
        return "ad-form-update";
    }


    @RequestMapping("/ads-update/submit")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String updateAd(@RequestParam("ad-form-picture") MultipartFile file, HttpServletRequest req, Model theModel) throws Exception{
        Ad ad = adService.getAdById(Integer.parseInt(req.getParameter("ad-form-id")));

        ad.setSpotType(req.getParameter("ad-form-type"));
        ad.setStation(adUtils(req.getParameter("ad-form-station")));
        ad.setSpotNumber(adUtils(req.getParameter("ad-form-spotNumber")));
        ad.setTrain(adUtils(req.getParameter("ad-form-train")));
        ad.setAdType(req.getParameter("ad-form-adtype"));
        ad.setIndustryType(req.getParameter("ad-form-industrytype"));
        ad.setContent(req.getParameter("ad-form-content"));
        ad.setCompany(req.getParameter("ad-form-company"));
        ad.setDdl(req.getParameter("ad-form-ddl"));
        // update spot status
        Spot spot;
        if(ad.getSpotType().equals("站内")){
            spot = spotService.findSpot(ad.getSpotType(), ad.getStation(), ad.getSpotNumber(), "/");
        }else{
            spot = spotService.findSpot(ad.getSpotType(), "/", "/", ad.getTrain());
        }


        if(spot == null){
            theModel.addAttribute("id", ad.getId());
            theModel.addAttribute("error", "广告点位不存在！！");
            return "ad-form-update";
        }


        boolean isDroped = Boolean.parseBoolean(req.getParameter("ad-form-isdroped"));
        if(!ad.getIsdroped() && isDroped){ // drop the ad
            spotService.updateSpotStatus(spot.getId(), "未占用");

        }else if(ad.getIsdroped() && !isDroped){  //re-display ad
            if(spot.getStatus().equals("已占用")){
                theModel.addAttribute("id", ad.getId());
                theModel.addAttribute("error", "广告点位已被占用！");
                return "ad-form-update";
            }
            spotService.updateSpotStatus(spot.getId(), "已占用");
        }
        ad.setIsdroped(isDroped);




        // if upload a picture, change the record
        if(req.getParameter("ad-form-check") != null){
            String fileName = RandomStringUtils.randomAlphabetic(20) + "_" +file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory , fileName).toString();
            ad.setPicture(fileName);
            try{
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            }catch (Exception e){
                e.printStackTrace();
                return "error";
            }
        }
        ad.setRemark(req.getParameter("ad-form-remark"));



        // create new record
        UserInfoUserDetails userDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recordService.save(
                new Record(
                        userDetails.getUser().getId(),
                        userDetails.getUsername(),
                        req.getRemoteAddr(),
                        req.getRequestURI(),
                        "广告信息更改 广告记录ID: " + ad.getId(),
                        new Date()));

        adService.updateAd(ad);
        return getAdsShangaiPage(1, theModel);
    }



//   3. shangai

    @RequestMapping("/ads-shangai")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String getAdsShangai(Model theModel){

        return getAdsShangaiPage(1, theModel);
    }

    @RequestMapping("/ads-shangai/drop")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String dropAd(@RequestParam("id") Integer id, Model theModel, HttpServletRequest req){
        Ad ad = adService.getAdById(id);

        if(!ad.getIsdroped()){  // if the ad is not droped yet
            Integer spotId = ad.getSpotId();
            spotService.updateSpotStatus(spotId, "未占用");
            adService.dropAd(id);


            UserInfoUserDetails userDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            recordService.save(
                    new Record(
                            userDetails.getUser().getId(),
                            userDetails.getUsername(),
                            req.getRemoteAddr(),
                            req.getRequestURI(),
                            "广告下刊 广告记录ID: " + ad.getId(),
                            new Date()));


        }

        return getAdsShangaiPage(1, theModel);
    }



    @RequestMapping("/ads-shangai/page/{pageNo}")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String getAdsShangaiPage(@PathVariable(value = "pageNo") Integer pageNo, Model theModel){
        int pageSize = 10;
        APIResponse<List<Ad>> res = adService.getAll(pageSize, pageNo);
        theModel.addAttribute("ads", res.getResponse());
        theModel.addAttribute("currentPage", pageNo);
        theModel.addAttribute("totalPages", res.getTotalPages());
        theModel.addAttribute("totalItems", res.getTotalItems());
        return "ad-shangai";

    }

    private String adUtils(String field){
        if(field == null || field.equals("")){
            return "/";
        }else{
            return field;
        }
    }


}
