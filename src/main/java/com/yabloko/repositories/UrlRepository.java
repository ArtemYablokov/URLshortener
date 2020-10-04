package com.yabloko.repositories;

import com.yabloko.models.UrlObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlObject, Long> {
    Optional<UrlObject> findByShortUrl(String shortUrl);
    Optional<UrlObject> findByShortHashId(Long shortHashId);

}
