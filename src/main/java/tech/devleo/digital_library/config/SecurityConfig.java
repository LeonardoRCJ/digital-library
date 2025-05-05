package tech.devleo.digital_library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                        .frameOptions(frame -> frame.disable()) // ✅ forma atual sem deprecation
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/*").permitAll()
                        .anyRequest().permitAll()
                )
                .build();
    }
}
