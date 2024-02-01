package com.fastcampus.projectjavaboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // authorizeHttpRequests    : HTTP 요청에 대한 인가 설정을 구성하는 메서드
        // anyRequest()             : 설정한 경로 외에 모든 경로를 뜻함.
        // .permitAll()             : 해당 경로에 대한 모든 요청을 인가하는 메서드로,
        //                            인증된 사용자나 권한에 상관없이 모든 사용자가 접근할 수 있게 허용함.
        //                            반대 의미로는, 'denyAll()' 이 있음.
        // formLogin(withDefaults()):
        // httpBasic(withDefaults()):

        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }
}
