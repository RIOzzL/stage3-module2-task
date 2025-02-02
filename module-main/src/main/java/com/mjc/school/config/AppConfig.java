package com.mjc.school.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@Import(value = {
//        ControllerConfiguration.class})
@Configuration
@ComponentScan(basePackages = "com.mjc.school.*")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
