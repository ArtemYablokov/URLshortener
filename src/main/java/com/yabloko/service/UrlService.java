package com.yabloko.service;

import com.yabloko.models.UrlObject;
import com.yabloko.repositories.UrlRepository;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Optional;

@Service
public class UrlService {
    @Autowired
    UrlRepository urlRepository;

    @Autowired
    SuffixGenerator suffixGenerator;

    public String findByShortUrl(String shortUrl){
        Optional<UrlObject> byShortUrl = urlRepository.findByShortUrl(shortUrl);

        return null;
    }

    public String findByShortHashId(Long shortHashId) {
        Optional<UrlObject> byShortHashId = urlRepository.findByShortHashId(shortHashId);

        return null;
    }

    public String saveAndReturnShort(String userUrl) {

        String shortUrl = suffixGenerator.generateShortUrl(userUrl);
        Long shortHashId = suffixGenerator.generateHashCode(shortUrl);
        UrlObject urlObject = new UrlObject(shortHashId, userUrl, shortUrl);

        urlRepository.saveAndFlush(urlObject);
        return shortUrl;
    }

    public UrlObject findById(Long id){
        Optional<UrlObject> optional = urlRepository.findById(id);
        return optional.orElseGet(() -> new UrlObject(0L, "null", "null"));
    }

}