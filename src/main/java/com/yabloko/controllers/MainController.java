package com.yabloko.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController - для реализации REST
@Controller
public class MainController {
    @GetMapping("/main")
    public String getMainPage(Model model) {
        System.out.println("req");
        return "login";
    }

    @PostMapping("/login")
    public String recieve(@RequestParam("username") String userName,
                          @RequestParam("password") String password){
        System.out.println(userName + " " + password);
        return "login";
    }
}