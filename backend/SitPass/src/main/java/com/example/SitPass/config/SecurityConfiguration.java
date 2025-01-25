package com.example.SitPass.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    public final static String ROLE_ADMIN = "ROLE_ADMIN";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/auth").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/exercises/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/reviews/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/facilities").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/facilities/search/rate/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "api/facilities/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/day/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/disciplines/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/image/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "api/users/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "api/users/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/users").permitAll()
                        .requestMatchers(HttpMethod.PUT, "api/disciplines/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "api/facilities/search/day/**").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}