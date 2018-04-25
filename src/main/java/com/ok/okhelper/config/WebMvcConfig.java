package com.ok.okhelper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.web.bind.annotation.CrossOrigin.DEFAULT_ALLOWED_HEADERS;
import static org.springframework.web.bind.annotation.CrossOrigin.DEFAULT_ORIGINS;

/**
 * Author: zc
 * Date: 2018/4/25
 * Description:
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(DEFAULT_ORIGINS)
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders(DEFAULT_ALLOWED_HEADERS)
                .allowCredentials(true)
                .maxAge(3600);
    }
}

