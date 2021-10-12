package com.techgentsia.shorturl.controller;

import com.techgentsia.shorturl.exception.UrlIdNotFoundException;
import com.techgentsia.shorturl.exception.UrlNotAliveException;
import com.techgentsia.shorturl.services.ShortUrlService;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Api(value = "ShortUrl RedirectController to handle redirection", tags={ "ShortUrlRedirectController"})
public class ShortUrlControllerImpl implements ShortUrlController {

    private final ShortUrlService shortUrlService;

    public ShortUrlControllerImpl(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @Override
    public RedirectView redirectToActualUrl(String shortUrlId) {
        RedirectView redirectView = new RedirectView();
        try {
            redirectView.setUrl(shortUrlService.getActualUrl(shortUrlId));
        } catch (UrlNotAliveException e) {
            redirectView.setUrl("/error-410.html");
            e.printStackTrace();
        } catch (UrlIdNotFoundException e) {
            redirectView.setUrl("/error-404.html");
            e.printStackTrace();
        }
        return redirectView;
    }
}
