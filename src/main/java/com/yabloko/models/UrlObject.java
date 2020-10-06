package com.yabloko.models;

import javax.persistence.*;

@Entity
@Table(name = "url")
public class UrlObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long shortUrlHashId;
    private String userUrl;
    private String shortUrl;

    public UrlObject() { }
    public UrlObject(Long shortUrlHashId, String userUrl, String shortUrl) {
        this.shortUrlHashId = shortUrlHashId;
        this.userUrl = userUrl;
        this.shortUrl = shortUrl;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getShortUrlHashId() {
        return shortUrlHashId;
    }
    public void setShortUrlHashId(Long short_hash_id) {
        this.shortUrlHashId = short_hash_id;
    }
    public String getUserUrl() {
        return userUrl;
    }
    public void setUserUrl(String userUrl) {
        this.userUrl = userUrl;
    }
    public String getShortUrl() {
        return shortUrl;
    }
    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
