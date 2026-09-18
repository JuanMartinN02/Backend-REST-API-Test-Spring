package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
        // 1.- authenticate all requests
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        // 2.- Enable basic authetication (pop-up asking for credentials)
        http.httpBasic(Customizer.withDefaults());

        // 3.- have stateless Rest API
//        A stateless REST API is an application programming interface where the server does not store any
//        information (state) about the client session between requests. Every single incoming
//        HTTP request is completely independent and must contain all the information necessary for the server
//        to understand and process it.
        http.sessionManagement(
                session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS));

        // 4.- disabling
//        Because the vulnerability mechanism (automatic browser cookie attachment)
//        isn't present, CSRF protection is redundant and can be safely disabled.
        http.csrf().disable();

        return http.build();
    }

}
