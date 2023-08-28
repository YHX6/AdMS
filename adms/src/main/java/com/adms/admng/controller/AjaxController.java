package com.adms.admng.controller;

import com.adms.admng.dao.AjaxRepository;
import com.adms.admng.entity.Ad;
import com.adms.admng.service.AdService;
import com.adms.admng.service.AjaxService;
import com.adms.admng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AjaxController {

    private AjaxService ajaxService;
    private AdService adService;

    private UserService userService;

    @Autowired
    public AjaxController(AjaxService ajaxService, AdService adService, UserService userService) {
        this.ajaxService = ajaxService;
        this.adService = adService;
        this.userService = userService;
    }

    //search bar
    @RequestMapping("/ajax/stations")
    public List<String> findAllStations(){
        return ajaxService.findAllStations();
    }

    @RequestMapping("/ajax/trains-stations")
    public List<String> findAllTrainsIncludingStation(){
        return ajaxService.findAllTrainsIncludingStation();
    }

    @RequestMapping("/ajax/stations-trains")
    public List<String> findAllStationsIncludingTrain(){
        return ajaxService.findAllStationsIncludingTrain();
    }
    @RequestMapping("/ajax/trains")
    public List<String> findAllTrains(){
        return ajaxService.findAllTrains();
    }

    @RequestMapping("/ajax/spotnumbers")
    public List<String> findAllSpotNumbers(String station){

//        System.out.println(station);
        return ajaxService.findAllSpotNumbers(station);
    }
    @RequestMapping("/ajax/spotnumbers-nostation")
    public List<String> findAllSpotNumbers(){

        return ajaxService.findAllSpotNumbers();
    }

    @RequestMapping("/ajax/ad-types")
    public List<String> findAllAdTypes(){

        return ajaxService.findAllAdTypes();
    }

    @RequestMapping("/ajax/industry-type")
    public List<String> findAllIndustryType(){

        return ajaxService.findAllAdIndustries();
    }

    @RequestMapping("/ajax/ad-id")
    public Ad findAdById(Integer id){
//        Ad ad = adService.getAdById(id);
//        System.out.println(ad.toString());
//        return null;
        return adService.getAdById(id);
    }


    @GetMapping("/ajax/username-exist")
    public boolean isUsernameExisted(String username){
        return userService.findExistByUsername(username);
    }

}
