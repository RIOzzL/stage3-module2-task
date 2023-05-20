package com.mjc.school.config;

import com.mjc.school.repository.config.RepoConfig;
import com.mjc.school.service.config.ServiceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Import(value = {RepoConfig.class,
        ServiceConfig.class})
@Configuration
@ComponentScan(basePackages = "com.mjc.school")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
