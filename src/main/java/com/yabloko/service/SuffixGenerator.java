package com.yabloko.service;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SuffixGenerator {

    public Long generateHashCode(String shortUrl){
        long hashCode = shortUrl.hashCode();
        if (hashCode < 0)
            hashCode = hashCode * -2;
        return hashCode;
    }

    public String generateShortUrlSuffix() {
        String suffix = RandomStringUtils.random(8, true, true);
        return suffix;
    }
}