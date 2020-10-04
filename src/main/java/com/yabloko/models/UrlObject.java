package com.yabloko.models;

import javax.persistence.*;

@Entity
@Table(name = "url")
public class UrlObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long shortHashId;
    private String userUrl;
    private String shortUrl;

    public UrlObject() { }
    public UrlObject(Long shortHashId, String userUrl, String shortUrl) {
        this.shortHashId = shortHashId;
        this.userUrl = userUrl;
        this.shortUrl = shortUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShortHashId() {
        return shortHashId;
    }

    public void setShortHashId(Long short_hash_id) {
        this.shortHashId = short_hash_id;
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
