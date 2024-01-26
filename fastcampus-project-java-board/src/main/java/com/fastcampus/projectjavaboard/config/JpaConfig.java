package com.fastcampus.projectjavaboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        // TODO: 스프링 시큐리티로 인증 기능을 붙이게 될 때(누가 인증하는지 식별하게 될 때), 수정하자.
        // 현재는 createdBy, modifiedBy 모두 aae123 으로 들어올 것
        return () -> Optional.of("aae1");
    }
}
