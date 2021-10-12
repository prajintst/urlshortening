package com.techgentsia.shorturl.config;

import com.techgentsia.shorturl.constants.SwaggerConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
@EnableOpenApi
public class SpringFoxConfig {

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SwaggerConstants.BASE_PACKAGE))
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(HttpAuthenticationScheme
                        .BASIC_AUTH_BUILDER
                        .name(SwaggerConstants.AUTH)
                        .build()));
    }

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(SwaggerConstants.TITLE)
                .description(SwaggerConstants.TITLE_DESC)
                .license(SwaggerConstants.LICENSE_URL)
                .licenseUrl(SwaggerConstants.LICENSE_URL)
                .termsOfServiceUrl("")
                .version("v1")
                .contact(new Contact("","", SwaggerConstants.CONTACT_EMAIL))
                .build();
    }

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title(SwaggerConstants.TITLE)
                        .description(SwaggerConstants.TITLE_DESC)
                        .termsOfService("")
                        .version("v1")
                        .license(new License()
                                .name("")
                                .url(""))
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .email(SwaggerConstants.CONTACT_EMAIL)));
    }
}