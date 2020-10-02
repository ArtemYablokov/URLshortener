package com.yabloko.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController - для реализации REST
@Controller
public class MainController {
    @GetMapping("/")
    public String getMainPage(){
        System.out.println("req");
        return "main";
    }
}