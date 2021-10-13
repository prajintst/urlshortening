package com.techgentsia.shorturl.services;

import com.techgentsia.shorturl.config.ShortUrlProperties;
import com.techgentsia.shorturl.entity.ShortUrl;
import com.techgentsia.shorturl.exception.UrlNotFoundException;
import com.techgentsia.shorturl.repository.ShortUrlRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.logging.Logger;

@Service
public class BackgroundCheckService {
    private final
    ShortUrlRepository shortUrlRepository;

    private final
    UrlStatusService urlStatusService;
    private final
    ShortUrlProperties properties;

    public static final Logger log = Logger.getLogger(BackgroundCheckService.class.getName());

    public BackgroundCheckService(ShortUrlRepository shortUrlRepository, UrlStatusService urlStatusService, ShortUrlProperties properties) {
        this.shortUrlRepository = shortUrlRepository;
        this.urlStatusService = urlStatusService;
        this.properties = properties;
    }

    public void checkUrls(){
        log.info("checking urls");
        ShortUrl shortUrl;
        while((shortUrl = shortUrlRepository.findTopByCheckedAtBeforeOrderByCheckedAt(Instant.now().minusSeconds(properties.getUrlCheckDelayInMin()*60)))!=null) {
            try {
                urlStatusService.checkUrlExists(shortUrl.getUrl());
                log.info(shortUrl.getUrl() + " is active");
                shortUrl.setIsAlive(true);
            } catch (UrlNotFoundException e) {
                log.info(shortUrl.getUrl() + " is not active");
                shortUrl.setIsAlive(false);
                e.printStackTrace();
            }
            shortUrl.setCheckedAt(Instant.now());
            shortUrlRepository.save(shortUrl);
        }
        log.info("checking urls completed");

    }
}
