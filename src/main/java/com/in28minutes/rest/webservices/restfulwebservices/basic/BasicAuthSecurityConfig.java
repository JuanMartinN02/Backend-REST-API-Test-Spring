package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthSecurityConfig {
    // Security filters chain

    // 1.- Disabling CSRF (Authenticate all requests)
//    stands for Cross-Site Request Forgery (sometimes called XSRF or "sea-surf").
//    It is a security exploit that tricks a logged-in user into performing unwanted
//    actions on a web application without their knowledge, mostly by a malicious website
//    sending request to our site bia the users browser.


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 1. Disable CSRF for stateless API
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Set stateless session management
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 3. Configure authorization rules
                .cors(Customizer.withDefaults()) // Handles OPTIONS preflight requests automatically
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )

                // 4. Enable HTTP Basic Auth
                .httpBasic(Customizer.withDefaults())

                // 5. Build and return the filter chain
                .build();
    }

}
