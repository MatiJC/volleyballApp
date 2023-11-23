package com.maticuad.volleyballApp.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class AdminController {
    @GetMapping("/admin")
    public String helloAdmin() {
        return "Admin access level";
    }

}
