package com.techgentsia.shorturl.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "shorturl")
@Setter
@Getter
public class ShortUrlProperties {

    private String user;
    private String password;
    private String baseUrl;
    private String swaggerBaseUris;
    private Integer urlCheckDelayInMin;
}