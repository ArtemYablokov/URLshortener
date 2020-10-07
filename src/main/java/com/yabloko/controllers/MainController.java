package com.yabloko.controllers;

import com.yabloko.models.UrlObject;
import com.yabloko.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.yabloko.Application.urlRepository;

//@RestController
@Controller
@RequestMapping("/get")
public class MainController {

    private static final String REGEX = "((http|https)://)(www.)?" /*https://www.*/
            + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}" /*google*/
            + "\\.[a-z]{2,6}" /*.com*/
            + "\\b([-a-zA-Z0-9а-я!@:%._\\+~#?&//=]*)"; // а-я! добавлено

    private static final String REGEX_TEMPLATE = "http(s?)://\\w(\\w|\\.)*\\.[a-z]{2,6}.*";

    @Value("${host.prefix}")
    private String hostPrefix;

    @Autowired
    UrlService urlService;

    @GetMapping("/main")
    public String getMainPage() {
        return "main";
    }

    @PostMapping("/main")
    public String receive(@RequestParam("userUrl") String userUrl, Model model) {
        String allert = "URL is valid";
        String shortUrl;
        if (userUrl.contains(" ")) {
            allert = "URL must not contain spaces";
        } else if (userUrl.isEmpty())
            allert = "URL must not be empty";
        else if (userUrl.length() > 2047)
            allert = "URL must not be shorter than 2047";
        else {
            Pattern compile = Pattern.compile(REGEX);
            Matcher matcher = compile.matcher(userUrl);
            boolean matches = matcher.matches();
            if (!matches) {
                allert = "URL is incorrect regex";
            } else {
                try {
                    new URL(userUrl); // cheking
                    shortUrl = urlService.saveAndReturnShort(userUrl);
                    model.addAttribute("shortUrl", shortUrl);

                } catch (MalformedURLException e) {
                    System.out.println("invalid URL, exception : " + e.toString());
                    allert = "URL is incorrect exception";
                }
            }
        }

        model.addAttribute("userUrl", userUrl);
//        model.addAttribute("shortUrl", userUrl);
        model.addAttribute("allert", allert);
        return "main";
    }

    // 5 сек для 1000 вставок
//    @GetMapping("/insert")
//    public String insert() {
//        String commonUrl = "https://google.com/";
//        String userUrl = "";
//        userUrl = commonUrl + "/suffix";
//        urlService.saveAndReturnShort(userUrl);
//        return "main";
//    }

    // 2 сек
//    @GetMapping("/check")
//    public String check() {
//
//        Set<Long> hashes = new HashSet<>();
//        Set<String> shortUrls = new HashSet<>();
//        Set<String> urls = new HashSet<>();
//
//        for (long i = 1; i < 1_001; i++) {
//            UrlObject urlObject = urlService.findById(i);
//
//            shortUrls.add(urlObject.getShortUrl());
//            hashes.add(urlObject.getShortUrlHashId());
//            urls.add(urlObject.getUserUrl());
//        }
//
//        System.out.println(hashes.size());
//        System.out.println(shortUrls.size());
//        System.out.println(urls.size());
//
//        return "main";
//    }

    @GetMapping("/errorurl")
    public String test(Model model, ModelAndView modelAndView, @RequestParam(required = false)String userSuffix) {

        model.addAttribute("allert", "error - no such url");
        if (userSuffix != null)
            model.addAttribute("userUrl", hostPrefix + userSuffix);
        return "main";
    }
}