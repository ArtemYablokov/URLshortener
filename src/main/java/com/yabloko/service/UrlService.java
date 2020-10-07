package com.yabloko.service;

import com.yabloko.models.UrlObject;
import com.yabloko.repositories.UrlRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UrlService {
    @Value("${host.prefix}")
    private String hostPrefix;
    @Autowired
    UrlRepository urlRepository;

    public String findBySuffix (String suffix) {
        String userUrl = hostPrefix + "errorurl";
        Long shortUrlHashId = generateHashCode(suffix);
        List<UrlObject> urlObjectList = urlRepository.findBySuffixHashId((shortUrlHashId));
        UrlObject urlObjectList2 = urlRepository.findByShortUrl((hostPrefix + suffix));

        if (urlObjectList.size() == 1){
            userUrl = urlObjectList.get(0).getUserUrl();
        }

        return userUrl;
    }

    public String saveAndReturnShort(String userUrl) {

        UrlObject urlExists = urlRepository.findByUserUrl(userUrl);
        if (urlExists != null)
            return "URL already exists!";

        String urlSuffix = generateUrlSuffix();
        Long urlHashId = generateHashCode(urlSuffix);
        String shortUrl = hostPrefix + urlSuffix;

        UrlObject urlObject = new UrlObject(urlHashId, userUrl, shortUrl);
        urlRepository.saveAndFlush(urlObject);
        return shortUrl;
    }


    public Long generateHashCode(String urlSuffix){
        long hashCode = urlSuffix.hashCode();
        if (hashCode < 0)
            hashCode = hashCode * -2;
        return hashCode;
    }

    public String generateUrlSuffix() {
        String suffix = RandomStringUtils.random(8, true, true);
        String userUrl = "";
        // check if suffix already exists
        while (userUrl != null) {
            userUrl = findBySuffix(suffix);
            suffix = RandomStringUtils.random(8, true, true);
        }
        return suffix;
    }


    public UrlObject findById(Long id){
        Optional<UrlObject> optional = urlRepository.findById(id);
        return optional.orElseGet(() -> new UrlObject(0L, "null", "null"));
    }




//    public String findByShortUrl(String shortUrl){
////        Optional<UrlObject> byShortUrl = urlRepository.findByShortUrl(shortUrl);
//        return null;
//    }
}