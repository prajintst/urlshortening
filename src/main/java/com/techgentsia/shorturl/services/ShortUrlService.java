package com.techgentsia.shorturl.services;

import com.techgentsia.shorturl.config.ShortUrlProperties;
import com.techgentsia.shorturl.entity.ShortUrl;
import com.techgentsia.shorturl.exception.UrlIdNotFoundException;
import com.techgentsia.shorturl.exception.UrlNotAliveException;
import com.techgentsia.shorturl.exception.UrlNotFoundException;
import com.techgentsia.shorturl.repository.ShortUrlRepository;
import com.techgentsia.shorturl.util.IdUtil;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class ShortUrlService {

    private final
    ShortUrlRepository shortUrlRepository;

    private final
    UrlStatusService urlStatusService;

    private final
    ShortUrlProperties properties;

    public ShortUrlService(ShortUrlRepository shortUrlRepository, UrlStatusService urlStatusService, ShortUrlProperties properties) {
        this.shortUrlRepository = shortUrlRepository;
        this.urlStatusService = urlStatusService;
        this.properties = properties;
    }

    public ShortUrl createShortUrl(ShortUrl shortUrl) throws UrlNotFoundException{
        urlStatusService.checkUrlExists(shortUrl.getUrl());
        ShortUrl shortUrlGenerated;
        try{
            shortUrlGenerated = shortUrlRepository.save(shortUrl);
        } catch (Exception e){
            shortUrlGenerated = shortUrlRepository.findFirstByUrl(shortUrl.getUrl());
        }
        shortUrlGenerated.setShortUrl(properties.getBaseUrl()+ IdUtil.idToString(shortUrl.getId()));
        return shortUrlGenerated;
    }

    public String getActualUrl(String shortUrlId) throws UrlNotAliveException, UrlIdNotFoundException {
        ShortUrl url = shortUrlRepository.findById(IdUtil.stringToId(shortUrlId)).orElseThrow(UrlIdNotFoundException::new);
        if(url.getIsAlive()) {
            return url.getUrl();
        }
        throw new UrlNotAliveException();
    }
}
