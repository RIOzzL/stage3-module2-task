package com.mjc.school.service.impl;

import com.mjc.school.repository.model.entity.News;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService implements BaseService<NewsRequestDto, News, Long> {



    @Override
    public List<News> readAll() {
        return null;
    }

    @Override
    public News readById(Long id) {
        return null;
    }

    @Override
    public News create(NewsRequestDto createRequest) {
        return null;
    }

    @Override
    public News update(NewsRequestDto updateRequest) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
