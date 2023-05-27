package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.validator.restriction.CreateValid;
import com.mjc.school.service.validator.restriction.IsEntityExist;
import com.mjc.school.service.validator.restriction.UpdateValid;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class NewsController implements BaseController<NewsDto, NewsDto, Long> {

    private final NewsService newsService;
    private final ModelMapper mapper;

    @Autowired
    public NewsController(NewsService newsService, ModelMapper mapper) {
        this.newsService = newsService;
        this.mapper = mapper;
    }


    @Override
    public List<NewsDto> readAll() {
        return newsService.readAll().stream()
                .map(newsDto -> mapper.map(newsDto, NewsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public NewsDto readById(@IsEntityExist Long id) {
        NewsDto newsDto = newsService.readById(id);
        return mapper.map(newsDto, NewsDto.class);
    }

    @Override
    @CreateValid
    public NewsDto create(NewsDto createRequest) {
        NewsDto newsDto = newsService.create(mapper.map(createRequest, NewsDto.class));
        return mapper.map(newsDto, NewsDto.class);
    }

    @Override
    @UpdateValid
    public NewsDto update(NewsDto updateRequest) {
        NewsDto update = newsService.update(mapper.map(updateRequest, NewsDto.class));
        return mapper.map(update, NewsDto.class);
    }

    @Override
    public boolean deleteById(@IsEntityExist Long id) {
        return newsService.deleteById(id);
    }
}
