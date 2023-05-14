package com.mjc.school.utils;

import com.mjc.school.controller.impl.NewsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.utils.Constants.*;
import static com.mjc.school.utils.Operations.*;

@Component
public class Menu {

    private final NewsController newsController;

    @Autowired
    public Menu(NewsController newsController) {
        this.newsController = newsController;
    }


    public void printMenu() {
        System.out.println("Enter the number of operation:");
        for (Operations value : values()) {
            System.out.println(value.getOperationNumber() + " - " + value.getOperationDescription());
        }
    }

    public void printAllNews() {
        System.out.println(newsController.getAllNews());
    }

    public void printNewsById(NewsController newsController, Scanner scanner) {
        System.out.println(OPERATION + GET_NEWS_BY_ID.getOperationDescription());
        System.out.println(ENTER_NEWS_ID);
        try {
            NewsDto newsById = newsController.getNewsById(validateNumberInput(scanner, NEWS_ID));
            System.out.println(newsById);
        } catch (ValidatorException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void createNews(NewsController newsController, Scanner scanner) {
        System.out.println(OPERATION + CREATE_NEWS.getOperationDescription());
        System.out.println(ENTER_NEWS_TITLE);
        NewsDto newsDto = new NewsDto();
        scanner.nextLine();
        newsDto.setTitle(scanner.nextLine());
        System.out.println(ENTER_NEWS_CONTENT);
        newsDto.setContent(scanner.nextLine());
        System.out.println(ENTER_AUTHOR_ID);
        try {
            newsDto.setAuthorId(validateNumberInput(scanner, AUTHOR_ID));
            System.out.println(newsController.createNews(newsDto));
        } catch (ValidatorException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void updateNews(NewsController newsController, Scanner scanner) {
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
            System.out.println(newsController.updateNews(newsDto));
        } catch (ValidatorException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void removeNewsById(NewsController newsController, Scanner scanner) {
        System.out.println(OPERATION + REMOVE_NEWS_BY_ID.getOperationDescription());
        System.out.println(ENTER_NEWS_ID);
        long id = validateNumberInput(scanner, NEWS_ID);
        try {
            System.out.println(newsController.removeNews(id));
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