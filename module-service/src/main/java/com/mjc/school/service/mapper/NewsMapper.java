package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.entity.News;
import com.mjc.school.service.dto.NewsRequestDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class NewsMapper implements BaseMapper<News, NewsRequestDto> {

    private final ModelMapper mapper;

    @Autowired
    public NewsMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public NewsRequestDto toEntity(News news) {
        return null;
    }

    @Override
    public News toDto(NewsRequestDto newsRequestDto) {
        return null;
    }
}
