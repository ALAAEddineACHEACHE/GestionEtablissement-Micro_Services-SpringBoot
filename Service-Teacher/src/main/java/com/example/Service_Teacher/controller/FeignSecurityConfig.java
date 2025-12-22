package com.example.Service_Teacher.controller;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;


@Configuration
public class FeignSecurityConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null && auth.getCredentials() instanceof Jwt jwt) {
                requestTemplate.header(
                        "Authorization",
                        "Bearer " + jwt.getTokenValue()
                );
            }
        };
    }
}
