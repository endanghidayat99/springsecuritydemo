package com.endang.springsecuritydemo.controllers;

import com.endang.springsecuritydemo.models.UserProfile;
import com.endang.springsecuritydemo.repositories.UserProfileRepositories;
import com.endang.springsecuritydemo.utils.EncryptedPasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class PortalController {
    @Autowired
    UserProfileRepositories repositories;

    @GetMapping("/")
    public String view() {
        return "main";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userprofile",new UserProfile());
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(@Valid @ModelAttribute UserProfile user) {
        user.setPassword(EncryptedPasswordUtils.encrptPassword(user.getPassword()));
        user.setRole("ROLE_USER");
        repositories.save(user);
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model,Principal principal) {
        model.addAttribute("username",principal.getName());
        return "profile";
    }

    @GetMapping("/portallogin")
    public String login(Model model) {
        return "portallogin";
    }

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {
        model.addAttribute("username",principal.getName());
        return "admin";
    }

    @GetMapping("/403")
    public String forbiden() {
        return "forbiden";
    }



}
