package com.techgentsia.shorturl.api;

import com.techgentsia.shorturl.entity.ShortUrl;
import com.techgentsia.shorturl.exception.UrlNotFoundException;
import com.techgentsia.shorturl.services.ShortUrlService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "API to create short urls", tags={ "ShortenUrlAPI"})
public class ShortenUrlApiImpl implements ShortenUrlApi {

    private final ShortUrlService shortUrlService;

    public ShortenUrlApiImpl(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @Override
    public ShortUrl createShortUrl(ShortUrl shortUrl) throws UrlNotFoundException {
        return shortUrlService.createShortUrl(shortUrl);
    }
}
