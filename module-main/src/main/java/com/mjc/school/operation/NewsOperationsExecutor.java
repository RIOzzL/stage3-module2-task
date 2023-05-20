package com.mjc.school.operation;

import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.service.dto.NewsDto;
import com.mjc.school.service.exception.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.service.exception.ServiceError.VALIDATE_INT_VALUE;
import static com.mjc.school.utils.Constants.*;
import static com.mjc.school.utils.Operations.*;

@Component
public class NewsOperationsExecutor {

    private final NewsController newsController;
    private final Scanner scanner;

    @Autowired
    public NewsOperationsExecutor(NewsController newsController) {
        this.newsController = newsController;
        this.scanner = new Scanner(System.in);
    }

    public void printAllNews() {
        System.out.println(newsController.readAll());
    }

    public void getNewsById() {
        System.out.println(OPERATION + GET_NEWS_BY_ID.getOperationDescription());
        System.out.println(ENTER_NEWS_ID);
        try {
            long id = validateNumberInput(scanner, NEWS_ID);
            System.out.println(newsController.readById(id));
        } catch (ValidatorException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void createNews() {
        System.out.println(OPERATION + CREATE_NEWS.getOperationDescription());
        System.out.println(ENTER_NEWS_TITLE);
        NewsDto newsDto = new NewsDto();
        try {
            String title = scanner.nextLine();
            newsDto.setTitle(title);
            System.out.println(ENTER_NEWS_CONTENT);
            String content = scanner.nextLine();
            newsDto.setContent(content);
            System.out.println(ENTER_AUTHOR_ID);
            long authorId = validateNumberInput(scanner, AUTHOR_ID);
            newsDto.setAuthorId(authorId);
            System.out.println(newsController.create(newsDto));
        } catch (ValidatorException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void updateNews() {
        System.out.println(OPERATION + UPDATE_NEWS.getOperationDescription());
        System.out.println(ENTER_NEWS_ID);
        NewsDto newsDto = new NewsDto();
        try {
            newsDto.setId(validateNumberInput(scanner, NEWS_ID));
            System.out.println(ENTER_NEWS_TITLE);
            scanner.nextLine();
            newsDto.setTitle(scanner.nextLine());
            System.out.println(ENTER_NEWS_CONTENT);
            newsDto.setContent(scanner.nextLine());
            System.out.println(ENTER_AUTHOR_ID);
            newsDto.setAuthorId(validateNumberInput(scanner, AUTHOR_ID));
            System.out.println(newsController.update(newsDto));
        } catch (ValidatorException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void deleteNewById() {
        System.out.println(OPERATION + REMOVE_NEWS_BY_ID.getOperationDescription());
        System.out.println(ENTER_NEWS_ID);
        try {
            long id = validateNumberInput(scanner, NEWS_ID);
            System.out.println(newsController.deleteById(id));
        } catch (ValidatorException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public Long validateNumberInput(Scanner scanner, String param) {
        long nextLong;
        try {
            nextLong = scanner.nextLong();
        } catch (RuntimeException exception) {
            throw new ValidatorException(String.format(VALIDATE_INT_VALUE.getMessage(), param));
        }
        return nextLong;
    }
}