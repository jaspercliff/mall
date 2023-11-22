package com.jasper.config;

import com.jasper.component.JwtAuthenticationTokenFilter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * @author com
 * 6/20/2023  10:27 AM
 */
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final static Logger LOGGER =  LoggerFactory.getLogger(SecurityConfig.class);
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    private final IgnoreUrlsConfig ignoreUrlsConfig;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable).
                sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
                authorizeHttpRequests(auth ->
                auth.requestMatchers(
                        ignoreUrlsConfig.getUrls().toArray(new String[0])
                        ).permitAll().
                requestMatchers(HttpMethod.OPTIONS).permitAll().
                anyRequest().authenticated()).
                addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class).
                build();
    }
}
