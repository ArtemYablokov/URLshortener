package com.yabloko.repositories;

import com.yabloko.models.UrlObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlObject, Long> {
//    Optional<UrlObject> findByShortUrl(String shortUrl);

//    @Query(value = "SELECT user_url FROM url where short_url_hash_id = ?1")
//    String getIt(Long short_url_hash_id);

//    @Query(value = "SELECT u FROM url u where u.shortUrlHashId = :#{#urlObject.shortUrlHashId}")
//    Optional<UrlObject> getIt(@Param("short_url_hash_id") UrlObject urlObject);


//    @Query(value = "SELECT user_url FROM url u where u.shortUrlHashId = :short_url_hash_id")
//    String getIt(@Param("short_url_hash_id") Long urlObject);

    @Query("FROM UrlObject WHERE short_url_hash_id = ?1")
    List<UrlObject> findBySuffixHashId(Long short_url_hash_id);

    UrlObject findByUserUrl(String userUrl);
    UrlObject findByShortUrl(String shortUrl);

}