package com.adms.admng.controller;

import com.adms.admng.entity.Record;
import com.adms.admng.entity.Spot;
import com.adms.admng.security.UserInfoUserDetails;
import com.adms.admng.service.RecordService;
import com.adms.admng.service.SpotService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class SpotController {
    private SpotService spotService;
    private RecordService recordService;

    @Autowired
    public SpotController(SpotService spotService, RecordService recordService) {
        this.spotService = spotService;
        this.recordService = recordService;
    }

    // 1.staff view page
    @GetMapping("/spots-view")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public String getAllSpotView(Model theModel){
        return findPaginatedView(1, theModel);
    }

    @GetMapping("/spots-view/page/{pageNo}")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public String findPaginatedView(@PathVariable(value = "pageNo") int pageNo, Model theModel){
        // add charts data
//        PieChartData.getDataList();
//        List<List<Map<Object, Object>>> canvasjsDataList = PieChartData.getDataList();
//        theModel.addAttribute("dataPointsList", canvasjsDataList);
        theModel.addAttribute("chartData", getPieChartData());


        int pageSize = 5;
        Page<Spot> page = spotService.findPaginated(pageNo, pageSize);
        List<Spot> listSpot = page.getContent();

        theModel.addAttribute("currentPage", pageNo);
        theModel.addAttribute("totalPages", page.getTotalPages());
        theModel.addAttribute("totalItems", page.getTotalElements());
        theModel.addAttribute("spots", listSpot);
        return "spot-staff";
    }

    private List<List<Object>> getPieChartData() {
        return List.of(
                List.of("已占用", 60),
                List.of("未占用", 40),
                List.of("其他", 0)
        );
    }




    //2. manager page
    @GetMapping("/spots")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String getAllSpot(Model theModel){
        return findPaginated(1, theModel);
    }

    @PostMapping("/spots/save")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String saveSpot(@ModelAttribute("spot") Spot spot, HttpServletRequest req){
        spotService.save(spot);


        UserInfoUserDetails userDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recordService.save(
                new Record(
                        userDetails.getUser().getId(),
                        userDetails.getUsername(),
                        req.getRemoteAddr(),
                        req.getRequestURI(),
                        "Spot created/modified: " + spot.getStation()+"-"+spot.getSpotNumber()+"-"+spot.getTrain(),
                        new Date()));



        return "redirect:/spots";
    }

    @GetMapping("/spots/delete")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public String deleteUser(@RequestParam("id") Integer theId,  HttpServletRequest req){
        spotService.deleteById(theId);


        UserInfoUserDetails userDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recordService.save(
                new Record(
                        userDetails.getUser().getId(),
                        userDetails.getUsername(),
                        req.getRemoteAddr(),
                        req.getRequestURI(),
                        "Spot deleted, spotID: " + theId,
                        new Date()));





        return "redirect:/spots";
    }

    @GetMapping("/spots/page/{pageNo}")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model theModel){
        int pageSize = 10;
        Page<Spot> page = spotService.findPaginated(pageNo, pageSize);
        Spot spot = new Spot();
        Spot spot2 = new Spot();
        List<Spot> listSpot = page.getContent();

        theModel.addAttribute("currentPage", pageNo);
        theModel.addAttribute("totalPages", page.getTotalPages());
        theModel.addAttribute("totalItems", page.getTotalElements());
        theModel.addAttribute("spots", listSpot);
        theModel.addAttribute("spot", spot);
        theModel.addAttribute("spot2", spot2);
        return "spot-manage";
    }

}
