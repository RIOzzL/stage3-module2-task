package com.mjc.school.service.validator;

import com.mjc.school.service.dto.AuthorRequestDto;
import com.mjc.school.service.validator.restriction.Size;
import com.mjc.school.service.exception.ValidatorException;
import com.mjc.school.service.impl.AuthorService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;


import static com.mjc.school.service.exception.ServiceError.AUTHOR_ID_DOES_NOT_EXIST;
import static com.mjc.school.service.exception.ServiceError.VALIDATE_STRING_LENGTH;

@Component
public class AuthorDtoValidator implements Validator<AuthorRequestDto> {

    private final AuthorService authorService;

    @Autowired
    public AuthorDtoValidator(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public boolean isValid(AuthorRequestDto authorDto) {
        return false;
    }

    @SneakyThrows
    @Override
    public boolean updateValidation(AuthorRequestDto authorDto) {
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
    public boolean createValidation(AuthorRequestDto authorDto) {
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
    public String sizeAnnotationValidation(AuthorRequestDto authorDto) throws IllegalAccessException {
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
        boolean existById = authorService.existById(id);
        if (existById) {
            return true;
        } else {
            throw new ValidatorException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }
}
