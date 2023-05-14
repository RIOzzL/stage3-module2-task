package com.mjc.school;

import com.mjc.school.controller.impl.NewsController;
import com.mjc.school.repository.config.RepoConfig;
import com.mjc.school.utils.Menu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepoConfig.class);
//        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
//        NewsRepository newsRepository = context.getBean(NewsRepository.class);
//
//        System.out.println(authorRepository.readAll());
//
//        System.out.println();
//
//        System.out.println(newsRepository.readAll());

        NewsController newsController = context.getBean(NewsController.class);

        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(newsController);
        boolean start = true;
        while (start) {
            menu.printMenu();
            String userInput = scanner.next();
            switch (userInput) {
                case "1" -> menu.printAllNews();
                case "2" -> menu.printNewsById(newsController, scanner);
                case "3" -> menu.createNews(newsController, scanner);
                case "4" -> menu.updateNews(newsController, scanner);
                case "5" -> menu.removeNewsById(newsController, scanner);
                case "6" -> start = false;
                default -> System.out.println(userInput + " not found! Please make correct choice!");
            }
        }
    }
}
