package com.techgentsia.shorturl.api;

import com.techgentsia.shorturl.constants.SwaggerConstants;
import com.techgentsia.shorturl.entity.ShortUrl;
import com.techgentsia.shorturl.exception.UrlNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Api endpoints
 *
 * @author <a href="mailto:prajin@techgentsia.com">Prajin Prakash</a>
 * @version $Revision: 1.0 $
 */

public interface ShortenUrlApi {

    @Operation(summary = "Create new short Url", description = "Which consume a url and validate and create a short url and keep it in database",security = {
            @SecurityRequirement(name = SwaggerConstants.AUTH)    }, tags={ "ShortenUrlAPI" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShortUrl.class))),
            @ApiResponse(responseCode = "400", description = "Validation exception", content = @Content(mediaType = "application/json")) })
    @PostMapping(value = "/short-url",
            produces = { "application/json" })
    /**
     * create a shortened url
     *
     * @param shortUrl the actual url to shorten
     */
    ShortUrl createShortUrl(@Valid @RequestBody ShortUrl shortUrl) throws UrlNotFoundException;

}
