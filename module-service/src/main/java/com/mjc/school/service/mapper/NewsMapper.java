package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.entity.News;
import com.mjc.school.service.dto.NewsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class NewsMapper implements BaseMapper<NewsDto, News> {

    private final ModelMapper mapper;

    @Autowired
    public NewsMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public News toEntity(NewsDto newsResponseDto) {
        return Objects.isNull(newsResponseDto) ? null : mapper.map(newsResponseDto, News.class);
    }

    @Override
    public NewsDto toDto(News news) {
        return Objects.isNull(news) ? null : mapper.map(news, NewsDto.class);
    }
}
