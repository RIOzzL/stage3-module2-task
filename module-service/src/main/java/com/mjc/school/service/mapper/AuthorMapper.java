package com.mjc.school.service.mapper;

import com.mjc.school.repository.model.entity.Author;
import com.mjc.school.service.dto.AuthorDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class AuthorMapper implements BaseMapper<AuthorDto, Author> {

    private final ModelMapper mapper;

    @Autowired
    public AuthorMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Author toEntity(AuthorDto authorDto) {
        return Objects.isNull(authorDto) ? null : mapper.map(authorDto, Author.class);
    }

    @Override
    public AuthorDto toDto(Author author) {
        return Objects.isNull(author) ? null : mapper.map(author, AuthorDto.class);
    }
}
