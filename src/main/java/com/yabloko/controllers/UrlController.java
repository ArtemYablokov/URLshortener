package com.yabloko.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UrlController {
    @GetMapping("/yabloko/{suffix}")
    public ModelAndView urlRequest(@PathVariable String suffix, HttpServletRequest request){

        String redirectUrl = request.getScheme() + "://ru.vuejs.org/v2/guide/" + suffix;

        return new ModelAndView("redirect:" + redirectUrl);
    }
}