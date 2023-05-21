package com.mjc.school.config;

import com.mjc.school.controller.config.ControllerConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Import(value = {
        ControllerConfiguration.class})
@Configuration
@ComponentScan(basePackages = "com.mjc.school")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
