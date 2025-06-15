package com.github.andrei4226.storemanagement.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/interface-admin")
    public String adminPage() {
        return "interface-admin";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/interface-user")
    public String userPage() {
        return "interface-user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
