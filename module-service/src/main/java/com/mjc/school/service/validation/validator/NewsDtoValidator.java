package com.mjc.school.service.validation.validator;

import com.mjc.school.repository.impl.NewsRepository;
import com.mjc.school.repository.model.entity.News;
import com.mjc.school.service.dto.NewsDto;
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
public class NewsDtoValidator implements Validator<NewsDto> {

    private final NewsRepository newsRepository;
    private final AuthorDtoValidator authorDtoValidator;

    @Autowired
    public NewsDtoValidator(NewsRepository newsRepository, AuthorDtoValidator authorDtoValidator) {
        this.newsRepository = newsRepository;
        this.authorDtoValidator = authorDtoValidator;
    }


    @Override
    public boolean isValid(NewsDto newsDto) {
        return false;
    }

    @SneakyThrows
    @Override
    public boolean updateValidation(NewsDto newsDto) {
        isExistValidation(newsDto.getId());
        StringBuilder errorMessage = new StringBuilder();
        Field[] declaredFieldsOfNewsDto = newsDto.getClass().getDeclaredFields();
        List<Field> fieldsWithSizeAnnotation = Arrays.stream(declaredFieldsOfNewsDto).filter(field -> field.isAnnotationPresent(Size.class)).toList();
        if (!fieldsWithSizeAnnotation.isEmpty()) {
            errorMessage.append(sizeAnnotationValidation(newsDto));
        }
        if (!newsRepository.readAll().stream().map(News::getId).toList().contains(newsDto.getAuthorId())) {
            errorMessage.append(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), newsDto.getAuthorId())).append("\n");
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidatorException(errorMessage.toString());
        }
        return true;
    }

    @SneakyThrows
    @Override
    public boolean createValidation(NewsDto newsDto) {
        StringBuilder errorMessage = new StringBuilder();

        Field[] declaredFieldsOfNewsDto = newsDto.getClass().getDeclaredFields();
        List<Field> fieldsWithSizeAnnotation = Arrays.stream(declaredFieldsOfNewsDto).filter(field -> field.isAnnotationPresent(Size.class)).toList();

        if (!fieldsWithSizeAnnotation.isEmpty()) {
            errorMessage.append(sizeAnnotationValidation(newsDto));
        }
        if (newsDto.getAuthorId() != null) {
            if (!newsRepository.readAll().stream().map(News::getId).toList().contains(newsDto.getAuthorId())) {
                errorMessage.append(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), newsDto.getAuthorId())).append("\n");
            }
        }
        if (!errorMessage.isEmpty()) {
            throw new ValidatorException(errorMessage.toString());
        }
        return true;
    }

    @Override
    public String sizeAnnotationValidation(NewsDto newsDto) throws IllegalAccessException {
        Field[] declaredFieldsOfNewsDto = newsDto.getClass().getDeclaredFields();
        List<Field> fieldsWithSizeAnnotation = Arrays.stream(declaredFieldsOfNewsDto).filter(field -> field.isAnnotationPresent(Size.class)).toList();
        StringBuilder errorMessage = new StringBuilder();
        if (!fieldsWithSizeAnnotation.isEmpty()) {
            for (Field field : fieldsWithSizeAnnotation) {
                Size annotation = field.getAnnotation(Size.class);
                int minSize = annotation.min();
                int maxSize = annotation.max();
                field.setAccessible(true);
                String fieldSizeAnnotatedValue = (String) field.get(newsDto);
                if (fieldSizeAnnotatedValue.length() < minSize || fieldSizeAnnotatedValue.length() > maxSize) {
                    errorMessage.append(String.format(VALIDATE_STRING_LENGTH.getMessage(), field.getName(),
                            minSize, maxSize, field.getName(), newsDto.getTitle())).append("\n");
                }
            }
            return errorMessage.toString();
        }
        return "";
    }

    @Override
    public boolean isExistValidation(Long id) {
        Optional<News> news = newsRepository.readById(id);
        if (news.isPresent()) {
            return true;
        } else {
            throw new ValidatorException(String.format(NEWS_ID_DOES_NOT_EXIST.getMessage(), id));
        }
    }
}