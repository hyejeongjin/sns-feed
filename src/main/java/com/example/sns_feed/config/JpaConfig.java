package com.example.sns_feed.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
        basePackages = "com.example.sns_feed.domain.jpa"
)
public class JpaConfig {
}
