package com.yabloko;


import com.yabloko.models.UrlObject;
import com.yabloko.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.yabloko")
public class Application {

    @Autowired
    public static UrlRepository urlRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}