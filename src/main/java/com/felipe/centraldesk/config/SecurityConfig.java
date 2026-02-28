package com.felipe.centraldesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {})
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }

    //@Bean
    //public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //
    //http
    //       .csrf(csrf -> csrf.disable()) // API não usa CSRF
    //       .authorizeHttpRequests(auth -> auth
    //               .requestMatchers(
    //                       "/v3/api-docs/**",
    //                       "/swagger-ui/**",
    //                       "/swagger-ui.html"
    //               ).permitAll()
    //              .anyRequest().authenticated()
    //      )
    //      .httpBasic(Customizer.withDefaults()); // Basic Auth temporário
    //
    //   return http.build();
    //}
}