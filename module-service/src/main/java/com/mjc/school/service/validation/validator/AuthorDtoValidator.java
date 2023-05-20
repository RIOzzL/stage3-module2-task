package com.mjc.school.service.validation.validator;

import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.model.entity.Author;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.exception.ValidatorException;
import com.mjc.school.service.validation.restriction.Size;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mjc.school.service.exception.ServiceError.*;

@Component
public class AuthorDtoValidator implements Validator<AuthorDto> {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorDtoValidator(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean isValid(AuthorDto authorDto) {
        return false;
    }

    @SneakyThrows
    @Override
    public boolean updateValidation(AuthorDto authorDto) {
        isExistValidation(authorDto.getId());
        StringBuilder errorMessage = new StringBuilder();
        List<Field> fieldsWithSizeAnnotation = Arrays.stream(authorDto.getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(Size.class)).toList();
        if (!fieldsWithSizeAnnotation.isEmpty()) {
            errorMessage.append(sizeAnnotationValidation(authorDto));
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidatorException(errorMessage.toString());
        }
        return true;
    }

    @SneakyThrows
    @Override
    public boolean createValidation(AuthorDto authorDto) {
        StringBuilder errorMessage = new StringBuilder();
        List<Field> fieldsWithSizeAnnotation = Arrays.stream(authorDto.getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(Size.class)).toList();
        if (!fieldsWithSizeAnnotation.isEmpty()) {
            errorMessage.append(sizeAnnotationValidation(authorDto));
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidatorException(errorMessage.toString());
        }
        return true;
    }

    @Override
    public String sizeAnnotationValidation(AuthorDto authorDto) throws IllegalAccessException {
        List<Field> fieldsWithSizeAnnotation = Arrays.stream(authorDto.getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(Size.class)).toList();
        StringBuilder errorMessage = new StringBuilder();
        if (!fieldsWithSizeAnnotation.isEmpty()) {
            for (Field field : fieldsWithSizeAnnotation) {
                Size annotation = field.getAnnotation(Size.class);
                int minSize = annotation.min();
                int maxSize = annotation.max();
                field.setAccessible(true);
                String fieldSizeAnnotatedValue = (String) field.get(authorDto);
                if (fieldSizeAnnotatedValue.length() < minSize || fieldSizeAnnotatedValue.length() > maxSize) {
                    errorMessage.append(String.format(VALIDATE_STRING_LENGTH.getMessage(), field.getName(),
                            minSize, maxSize, field.getName(), field.get(authorDto))).append("\n");
                }
            }
            return errorMessage.toString();
        }
        return "";
    }

    @Override
    public boolean isExistValidation(Long id) {
        Optional<Author> author = authorRepository.readById(id);
        if (author.isPresent()) {
            return true;
        } else {
            throw new ValidatorException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }
}
