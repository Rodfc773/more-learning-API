package com.br.rodrigofc.more_learning_API.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private static final String[] PUBLIC_ROUTES = {
            "/api/v1/courses",
            "/api/v1/courses/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui.html/"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http){
        try{
            http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> {
                auth.requestMatchers(PUBLIC_ROUTES).permitAll();
                auth.anyRequest().authenticated();
            });

            return http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
