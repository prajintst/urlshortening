package com.techgentsia.shorturl.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.stereotype.Component;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Component
public class SpringfoxSwaggerHostResolver implements WebMvcOpenApiTransformationFilter {

    private final ShortUrlProperties properties;

    public SpringfoxSwaggerHostResolver(ShortUrlProperties properties) {
        this.properties = properties;
    }

    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
        OpenAPI swagger = context.getSpecification();
        List<Server> servers = new LinkedList<>();
        Arrays.stream(properties.getSwaggerBaseUris().split(",")).forEach(url->{ Server server =  new Server();server.
                setUrl(url);servers.add(server); });swagger.setServers(servers);return swagger; }
    @Override public boolean supports(DocumentationType docType) {
        return docType.equals(DocumentationType.OAS_30);
    }}