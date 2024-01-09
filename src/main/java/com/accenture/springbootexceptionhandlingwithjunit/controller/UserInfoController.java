package com.accenture.springbootexceptionhandlingwithjunit.controller;

import com.accenture.springbootexceptionhandlingwithjunit.entity.UserInfo;
import com.accenture.springbootexceptionhandlingwithjunit.repository.UserInfoRepository;
import com.accenture.springbootexceptionhandlingwithjunit.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    UserInfoRepository userInfoRepository;


    @PostMapping("/addUser")
    public UserInfo addUser(@RequestBody UserInfo userInfo){
        userInfo = userInfoService.addUser(userInfo);
        return userInfo;
    }

    @GetMapping("/getUser/{username}")
    private UserDetails getUserByName(@PathVariable String username)
    {
        return userInfoService.loadUserByUsername(username);
    }

}
