package com.yabloko.service;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class SuffixGenerator {
    private String prefix = "http://yablo.ko/";

    public Long generateHashCode(String shortUrl){
        long hashCode = shortUrl.hashCode();
        if (hashCode < 0)
            hashCode = hashCode * -2;
        return hashCode;
    }

    public String generateShortUrl(String userUrl) {
        String suffix = RandomStringUtils.random(8, true, true);
        return prefix + suffix;
    }
}