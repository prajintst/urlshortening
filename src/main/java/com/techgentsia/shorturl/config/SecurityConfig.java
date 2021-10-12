package com.techgentsia.shorturl.config;

import com.techgentsia.shorturl.constants.SwaggerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.logging.Logger;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final Logger log = Logger.getLogger(SecurityConfig.class.getName());

    private final ShortUrlProperties properties;

    public SecurityConfig(ShortUrlProperties properties) {
        this.properties = properties;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // TODO: simple basic in memory auth with hardcoded user role can be changed to high level authentication/authorization
        log.info("auth user = " + properties.getUser());
        log.info("auth password = " + properties.getPassword());
        auth.inMemoryAuthentication()
                .withUser(properties.getUser()).password(passwordEncoder().encode(properties.getPassword()))
                .authorities("ROLE_USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(SwaggerConstants.SWAGGER_PATH_WILDCARD,
                        SwaggerConstants.SWAGGER_RESOURCES_WILDCARD,
                        SwaggerConstants.SWAGGER_API_PATH,
                        SwaggerConstants.SWAGGER_REDIRECT_PATH)
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}