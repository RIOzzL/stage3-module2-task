package com.mjc.school.service.config;

import com.mjc.school.repository.config.RepoConfig;
import com.mjc.school.service.mapper.AuthorMapper;
import com.mjc.school.service.mapper.NewsMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        RepoConfig.class})
@ComponentScan("com.mjc.school.service")
public class ServiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public NewsMapper newsMapper() {
        return new NewsMapper(modelMapper());
    }

    @Bean
    public AuthorMapper authorMapper() {
        return new AuthorMapper(modelMapper());
    }

}
