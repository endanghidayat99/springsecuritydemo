package com.endang.springsecuritydemo.api;

import com.endang.springsecuritydemo.models.UserProfile;
import com.endang.springsecuritydemo.repositories.UserProfileRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserAPIController {

    @Autowired
    private UserProfileRepositories repositories;

    @GetMapping("/list")
    public List<UserProfile> listUser(){
        return (List<UserProfile>) repositories.findAll();
    }

    @GetMapping("/delete")
    public String delete(){
        repositories.deleteAll();
        return "OK";
    }
}
