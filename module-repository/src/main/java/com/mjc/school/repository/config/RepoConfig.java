package com.mjc.school.repository.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.mjc.school.repository")
@EnableAspectJAutoProxy
public class RepoConfig {

}
