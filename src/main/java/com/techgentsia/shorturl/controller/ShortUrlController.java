package com.techgentsia.shorturl.controller;

import com.techgentsia.shorturl.constants.SwaggerConstants;
import com.techgentsia.shorturl.exception.UrlIdNotFoundException;
import com.techgentsia.shorturl.exception.UrlNotAliveException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Web endpoints
 *
 * @author <a href="mailto:prajin@techgentsia.com">Prajin Prakash</a>
 * @version $Revision: 1.0 $
 */

public interface ShortUrlController {

    /**
     * redirectToSwagger For redirecting to swagger doc from home url
     */

    @Operation(summary = "Redirect to swagger url", description = "", tags={ "ShortUrlRedirectController" })
    @GetMapping(value = "/")
    @ApiIgnore
    default RedirectView redirectToSwagger() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(SwaggerConstants.SWAGGER_PATH);
        return redirectView;
    }

    /**
     * redirect to actual url from short url
     *
     * @param shortUrlId id of the shortened url to find the actual url expecting an 8 character symbol
     */

    @Operation(summary = "Redirect to actual url from short url", description = "", tags={ "ShortUrlRedirectController" })
    @GetMapping(value = "/{shortUrlId:[^\\.]{8}}")
    RedirectView redirectToActualUrl(@PathVariable String shortUrlId) throws UrlIdNotFoundException, UrlNotAliveException;


}
