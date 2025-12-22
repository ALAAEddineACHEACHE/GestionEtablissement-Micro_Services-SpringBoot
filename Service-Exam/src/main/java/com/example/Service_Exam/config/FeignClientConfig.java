package com.example.Service_Exam.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignClientConfig {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication instanceof org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken jwtAuth) {
                String token = jwtAuth.getToken().getTokenValue();
                requestTemplate.header("Authorization", "Bearer " + token);
            }
        };
    }

}
