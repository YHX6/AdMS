package com.jgchuanmei.admng.controller;

import com.jgchuanmei.admng.entity.Record;
import com.jgchuanmei.admng.entity.User;
import com.jgchuanmei.admng.security.UserInfoUserDetails;
import com.jgchuanmei.admng.service.RecordService;
import com.jgchuanmei.admng.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

//@Controller
@Controller
public class UserController {

    private UserService userService;
    private RecordService recordService;

    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, RecordService recordService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.recordService = recordService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAllUsers(Model theModel){
        List<User> users = userService.findAll();
        theModel.addAttribute("users", users);

        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        theModel.addAttribute("user1", user1);
        theModel.addAttribute("user2", user2);
        theModel.addAttribute("user3", user3);


        return "acc-manage";
    }

    @PostMapping("/users/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addUser(@ModelAttribute("user1") User user, Model theModel, HttpServletRequest req){

        // encoding the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonLocked(true);
        user.formatRoles();
        userService.save(user);


        UserInfoUserDetails userDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recordService.save(
                new Record(
                        userDetails.getUser().getId(),
                        userDetails.getUsername(),
                        req.getRemoteAddr(),
                        req.getRequestURI(),
                        "User created, username: " + user.getUsername(),
                        new Date()));

        return "redirect:/users";
    }

    @PostMapping("/users/update-info")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateUserInfo(@ModelAttribute("user2") User user, HttpServletRequest req){
        User originalUser = userService.findById(user.getId()).get();
//        user.setPassword(originalUser.get().getPassword());
        originalUser.setName(user.getName());
        originalUser.setPhoneNumber(user.getPhoneNumber());
        //change roles
        user.formatRoles();
        originalUser.setRoles(user.getRoles());

        // if locked now, set lock time
        if(!user.isAccountNonLocked() && originalUser.isAccountNonLocked()){
            originalUser.setLockTime(new Date());
        }else if(user.isAccountNonLocked() && !originalUser.isAccountNonLocked()){
            originalUser.setLockTime(null);
        }
        originalUser.setAccountNonLocked(user.isAccountNonLocked());

        user.formatRoles();
        userService.save(originalUser);


        UserInfoUserDetails userDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recordService.save(
                new Record(
                        userDetails.getUser().getId(),
                        userDetails.getUsername(),
                        req.getRemoteAddr(),
                        req.getRequestURI(),
                        "User info modified, username: " + user.getUsername(),
                        new Date()));

        return "redirect:/users";
    }


    @PostMapping("/users/update-password")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateUserPassword(@ModelAttribute("user3") User user, HttpServletRequest req){
        User originalUser = userService.findById(user.getId()).get();

        originalUser.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.save(originalUser);

        UserInfoUserDetails userDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recordService.save(
                new Record(
                        userDetails.getUser().getId(),
                        userDetails.getUsername(),
                        req.getRemoteAddr(),
                        req.getRequestURI(),
                        "Password modified by Admin, username: " + originalUser.getUsername(),
                        new Date()));
        return "redirect:/users";
    }

    @GetMapping("/users/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(@RequestParam("id") String theId, HttpSession session, HttpServletRequest req){

        userService.deleteById(theId);

        UserInfoUserDetails userDetails = (UserInfoUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recordService.save(
                new Record(
                        userDetails.getUser().getId(),
                        userDetails.getUsername(),
                        req.getRemoteAddr(),
                        req.getRequestURI(),
                        "User deleted, userID: " + theId,
                        new Date()));
        return "redirect:/users";
    }
}
