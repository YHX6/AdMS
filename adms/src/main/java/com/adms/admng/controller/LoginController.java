package com.adms.admng.controller;

import com.adms.admng.entity.Record;
import com.adms.admng.entity.ResetPassword;
import com.adms.admng.entity.User;
import com.adms.admng.security.UserInfoUserDetails;
import com.adms.admng.service.RecordService;
import com.adms.admng.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
public class LoginController {

    private UserService userService;


    private RecordService recordService;
    private PasswordEncoder passwordEncoder;
    SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @Autowired
    public LoginController(UserService userService,PasswordEncoder passwordEncoder, RecordService recordService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.recordService = recordService;
    }

    @RequestMapping("/login")
    public String login(){
        return "page-login";
    }

    @RequestMapping("/reset-password")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public String resetPassword(Model theModel){
        ResetPassword rp = new ResetPassword();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        rp.setUsername(username);
        theModel.addAttribute("rp", rp);
        theModel.addAttribute("errorUsername", "");
        theModel.addAttribute("errorPassword", "");

        return "page-reset-password";
    }

    @RequestMapping("/reset-password/submit")
    public String resetPasswordSubmit(@ModelAttribute("rp") ResetPassword rp, Model theModel, HttpServletRequest req){
//        Optional<User> user = userService.findUserByUsername(rp.getUsername());
//
//
//        if(user.isEmpty()){
//            theModel.addAttribute("errorPassword", "");
//            theModel.addAttribute("errorUsername", "User not existed!");
//            return "page-reset-password";
//        }else if(!passwordEncoder.matches(rp.getPasswordOld(), user.get().getPassword())){
//            theModel.addAttribute("errorUsername", "");
//            theModel.addAttribute("errorPassword", "Incorrect Password!");
//            return "page-reset-password";
//        }else{
//            user.get().setPassword(passwordEncoder.encode(rp.getPasswordNew()));
//            userService.save(user.get());
//
//
//            UserInfoUserDetails userDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//            recordService.save(
//                    new Record(
//                            userDetails.getUser().getId(),
//                            userDetails.getUsername(),
//                            req.getRemoteAddr(),
//                            req.getRequestURI(),
//                            "User password chanaged, username： " + rp.getUsername(),
//                            new Date()));
//
//            return "redirect:/logout";
//        }
        return "redirect:/logout";
    }


    @GetMapping("/logout")
    public String performLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "logout-success";
    }
}
