package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.validator.restriction.CreateValid;
import com.mjc.school.service.validator.restriction.IsEntityExist;
import com.mjc.school.service.validator.restriction.UpdateValid;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.impl.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AuthorController implements BaseController<AuthorDto, AuthorDto, Long> {

    private AuthorService authorService;
    private final ModelMapper mapper;

    @Autowired
    public AuthorController(AuthorService authorService, ModelMapper mapper) {
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @Override
    public List<AuthorDto> readAll() {
        return authorService.readAll().stream()
                .map(authorDto -> mapper.map(authorDto, AuthorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDto readById(@IsEntityExist Long id) {
        AuthorDto authorDto = authorService.readById(id);
        return mapper.map(authorDto, AuthorDto.class);
    }

    @Override
    @CreateValid
    public AuthorDto create(AuthorDto createRequest) {
        AuthorDto authorDto = authorService.create(mapper.map(createRequest, AuthorDto.class));
        return mapper.map(authorDto, AuthorDto.class);
    }

    @Override
    @UpdateValid
    public AuthorDto update(AuthorDto updateRequest) {
        AuthorDto update = authorService.update(mapper.map(updateRequest, AuthorDto.class));
        return mapper.map(update, AuthorDto.class);
    }

    @Override
    public boolean deleteById(@IsEntityExist Long id) {
        return authorService.deleteById(id);
    }
}
