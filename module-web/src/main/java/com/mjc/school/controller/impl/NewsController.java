package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.dto.NewsRequestDto;
import com.mjc.school.controller.validation.restriction.CreateValid;
import com.mjc.school.controller.validation.restriction.IsEntityExist;
import com.mjc.school.controller.validation.restriction.UpdateValid;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.impl.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class NewsController implements BaseController<NewsRequestDto, NewsRequestDto, Long> {

    private final NewsService newsService;
    private final ModelMapper mapper;

    @Autowired
    public NewsController(NewsService newsService, ModelMapper mapper) {
        this.newsService = newsService;
        this.mapper = mapper;
    }


    @Override
    public List<NewsRequestDto> readAll() {
        return newsService.readAll().stream()
                .map(newsDto -> mapper.map(newsDto, NewsRequestDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public NewsRequestDto readById(@IsEntityExist Long id) {
        NewsDto newsDto = newsService.readById(id);
        return mapper.map(newsDto, NewsRequestDto.class);
    }

    @Override
    @CreateValid
    public NewsRequestDto create(NewsRequestDto createRequest) {
        NewsDto newsDto = newsService.create(mapper.map(createRequest, NewsDto.class));
        return mapper.map(newsDto, NewsRequestDto.class);
    }

    @Override
    @UpdateValid
    public NewsRequestDto update(NewsRequestDto updateRequest) {
        NewsDto update = newsService.update(mapper.map(updateRequest, NewsDto.class));
        return mapper.map(update, NewsRequestDto.class);
    }

    @Override
    public boolean deleteById(@IsEntityExist Long id) {
        return newsService.deleteById(id);
    }
}
