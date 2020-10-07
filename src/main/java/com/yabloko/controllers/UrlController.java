package com.yabloko.controllers;

import com.yabloko.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
public class UrlController {
    @Value("${host.prefix}")
    private String hostPrefix;
    @Autowired
    UrlService urlService;

    //    редирект работает
//    кодировка - нет
//    https://yandex.ru/search/?lr=213&oprnd=6506004617&text=%3F%3F%3F%3F%3F
    @GetMapping("/{suffix}")
    public String stringRedirect(@PathVariable String suffix, HttpServletRequest request) throws UnsupportedEncodingException {
        String userUrl = urlService.findBySuffix(suffix);
        return "redirect:" + userUrl;
    }

    // редирект НЕ работает
    // кодировка - да
//    http://localhost:8080/get/https%3A%2F%2Fyandex.ru%2Fsearch%2F%3Flr%3D213%26oprnd%3D6506004617%26text%3Dзапрос
//    @GetMapping("/{suffix}")
    public String stringEncode(@PathVariable String suffix, HttpServletRequest request) throws UnsupportedEncodingException {
        String userUrl = urlService.findBySuffix(suffix);
        String encode = URLEncoder.encode(userUrl, "UTF-8");
        return "redirect:" + encode;
    }

    //    не отображает кирилицу
//     https://yandex.ru/search/?lr=213&oprnd=6506004617&text=%3F%3F%3F%3F%3F
//    @GetMapping("/{suffix}")
    public void httpRedirect(@PathVariable String suffix, HttpServletResponse httpServletResponse) {
        String userUrl = urlService.findBySuffix(suffix);
        httpServletResponse.setHeader("Location", userUrl);
        httpServletResponse.setStatus(302);
    }

    //    кодирует плохо
    //    https%3A%2F%2Fyandex.ru%2Fsearch%2F%3Flr%3D213%26oprnd%3D6506004617%26text%3D%D0%B7%D0%B0%D0%BF%D1%80%D0%BE%D1%81
//    @GetMapping(value = "/{suffix}")
    public ModelAndView modelAndViewEncoderequest(@PathVariable String suffix, Model model) throws UnsupportedEncodingException {
        String userUrl = urlService.findBySuffix(suffix);
        model.addAttribute("userUrl", userUrl);
        String encode = URLEncoder.encode(userUrl, "UTF-8");
        ModelAndView modelAndView = new ModelAndView(encode);
        return modelAndView;
    }


    // ModelAndView не отображает кирилицу
//    https://yandex.ru/search/?lr=213&oprnd=6506004617&text=%3F%3F%3F%3F%3F
//    @GetMapping("/{suffix}")
    public ModelAndView modelAndView(@PathVariable String suffix, HttpServletRequest request) {
        String userUrl = urlService.findBySuffix(suffix);
        ModelAndView modelAndView = new ModelAndView("redirect:" + userUrl);

        if (userUrl.equals("http://localhost:8080/errorurl"))
            modelAndView.getModelMap().addAttribute("userSuffix", suffix);
        return modelAndView;
    }
}