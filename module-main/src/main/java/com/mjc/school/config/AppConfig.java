package com.mjc.school.config;

import com.mjc.school.repository.config.RepoConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = RepoConfig.class)
@Configuration
@ComponentScan(basePackages = "com.mjc.school")
public class AppConfig {
}
