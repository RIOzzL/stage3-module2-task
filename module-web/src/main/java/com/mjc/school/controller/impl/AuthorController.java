package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorRequestDto;
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
public class AuthorController implements BaseController<AuthorRequestDto, AuthorRequestDto, Long> {

    private AuthorService authorService;
    private final ModelMapper mapper;

    @Autowired
    public AuthorController(AuthorService authorService, ModelMapper mapper) {
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @Override
    public List<AuthorRequestDto> readAll() {
        return authorService.readAll().stream()
                .map(authorDto -> mapper.map(authorDto, AuthorRequestDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorRequestDto readById(@IsEntityExist Long id) {
        AuthorDto authorDto = authorService.readById(id);
        return mapper.map(authorDto, AuthorRequestDto.class);
    }

    @Override
    @CreateValid
    public AuthorRequestDto create(AuthorRequestDto createRequest) {
        AuthorDto authorDto = authorService.create(mapper.map(createRequest, AuthorDto.class));
        return mapper.map(authorDto, AuthorRequestDto.class);
    }

    @Override
    @UpdateValid
    public AuthorRequestDto update(AuthorRequestDto updateRequest) {
        AuthorDto update = authorService.update(mapper.map(updateRequest, AuthorDto.class));
        return mapper.map(update, AuthorRequestDto.class);
    }

    @Override
    public boolean deleteById(@IsEntityExist Long id) {
        return authorService.deleteById(id);
    }
}
