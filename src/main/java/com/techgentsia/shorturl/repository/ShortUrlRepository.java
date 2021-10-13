package com.techgentsia.shorturl.repository;

import com.techgentsia.shorturl.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Integer> {

    ShortUrl findFirstByUrl(String s);

    ShortUrl findTopByCheckedAtBeforeOrderByCheckedAt(Instant time);

}
