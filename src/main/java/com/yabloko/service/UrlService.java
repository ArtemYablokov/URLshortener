package com.yabloko.service;

import com.yabloko.models.UrlObject;
import com.yabloko.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.yabloko.Application.urlRepository;

@Service
public class UrlService {
    @Value("${host.prefix}")
    private String hostPrefix;
    @Autowired
    UrlRepository urlRepository;
    @Autowired
    SuffixGenerator suffixGenerator;

    public String findBySuffixUrlHashId(String suffix) {
        String userUrl = hostPrefix + "errorurl";
        Long shortUrlHashId = suffixGenerator.generateHashCode(suffix);
        List<UrlObject> urlObjectList = urlRepository.findByShortUrlHashId((shortUrlHashId));

        if (urlObjectList.size() == 1)
            userUrl = urlObjectList.get(0).getUserUrl();
        return userUrl;
    }

    public String saveAndReturnShort(String userUrl) {
        String shortUrlSuffix = suffixGenerator.generateShortUrlSuffix();
        Long shortUrlHashId = suffixGenerator.generateHashCode(shortUrlSuffix);
        String shortUrl = hostPrefix + shortUrlSuffix;

        UrlObject urlObject = new UrlObject(shortUrlHashId, userUrl, shortUrl);
        urlRepository.saveAndFlush(urlObject);
        return shortUrl;
    }

    public UrlObject findById(Long id){
        Optional<UrlObject> optional = urlRepository.findById(id);
        return optional.orElseGet(() -> new UrlObject(0L, "null", "null"));
    }

    public String findByShortUrl(String shortUrl){
//        Optional<UrlObject> byShortUrl = urlRepository.findByShortUrl(shortUrl);
        return null;
    }
}