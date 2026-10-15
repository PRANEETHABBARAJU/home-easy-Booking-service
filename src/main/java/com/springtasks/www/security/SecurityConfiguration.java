package com.springtasks.www.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

    private final JwtAuthenticationFilter
        jwtAuthenticationFilter;

    public SecurityConfiguration(
            JwtAuthenticationFilter
                jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter =
            jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
            .cors(cors -> {})
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )

            .authorizeHttpRequests(auth -> auth

                // Only Customer can create a booking
                .requestMatchers(
                    HttpMethod.POST,
                    "/bookings"
                )
                .hasRole("CUSTOMER")

                // Customer booking operations
                .requestMatchers(
                    "/bookings/customer/**"
                )
                .hasRole("CUSTOMER")

                // Provider booking operations
                .requestMatchers(
                    "/bookings/provider/**"
                )
                .hasRole("PROVIDER")

                // Admin views every booking
                .requestMatchers(
                    HttpMethod.GET,
                    "/bookings/admin/all"
                )
                .hasRole("ADMIN")

                .anyRequest()
                .authenticated()
            )

            .exceptionHandling(exception ->
                exception.authenticationEntryPoint(
                    new HttpStatusEntryPoint(
                        HttpStatus.UNAUTHORIZED
                    )
                )
            )

            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}