package com.yabloko.controllers;

import com.yabloko.models.UrlObject;
import com.yabloko.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@RestController
@Controller
public class MainController {

    private static final String REGEX = "((http|https)://)(www.)?" /*https://www.*/
            + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}" /*google*/
            + "\\.[a-z]{2,6}" /*.com*/
            + "\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";

    private static final String REGEX_TEMPLATE = "http(s?)://\\w(\\w|\\.)*\\.[a-z]{2,6}.*";

    @Autowired
    UrlService urlService;

    @GetMapping("/main")
    public String getMainPage() {
        return "main";
    }

    @PostMapping("/main")
    public String recieve(@RequestParam("userUrl") String userUrl, Model model) {
        String allert = "URL is valid";
        if (userUrl.contains(" ")) {
            allert = "URL must not contain spaces";
        } else if (userUrl.isEmpty())
            allert = "URL must not be empty";
        else {
            Pattern compile = Pattern.compile(REGEX);
            Matcher matcher = compile.matcher(userUrl);
            boolean matches = matcher.matches();
            if ( !matches ) {
                allert = "URL is incorrect regex";
            } else {
                try {
                    new URL(userUrl); // cheking
                    String shortUrl = urlService.saveAndReturnShort(userUrl);
                    model.addAttribute("shortUrl", shortUrl);

                } catch (MalformedURLException e) {
                    System.out.println("invalid URL, exception : " + e.toString());
                    allert = "URL is incorrect exception";
                }
            }
        }

        model.addAttribute("userUrl", userUrl);
        model.addAttribute("shortUrl", userUrl);
        model.addAttribute("allert", allert);
        return "main";
    }

    // 5 сек
    @GetMapping("/insert")
    public String insert(){
        String commonUrl = "https://google.com/";
        String userUrl = "";
        for (int i = 0; i < 1_000; i++) {
            userUrl = commonUrl + i + "/suffix";
            urlService.saveAndReturnShort(userUrl);
        }
        return "main";
    }

    // 2 сек
    @GetMapping("/check")
    public String check(){

        Set<Long> hashes = new HashSet<>();
        Set<String> shortUrls = new HashSet<>();
        Set<String> urls = new HashSet<>();

        for (long i = 1; i < 1_001; i++) {
            UrlObject urlObject = urlService.findById(i);

            shortUrls.add(urlObject.getShortUrl());
            hashes.add(urlObject.getShortHashId());
            urls.add(urlObject.getUserUrl());
        }

        System.out.println(hashes.size());
        System.out.println(shortUrls.size());
        System.out.println(urls.size());

        return "main";
    }
}