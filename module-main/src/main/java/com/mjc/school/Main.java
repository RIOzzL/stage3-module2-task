package com.mjc.school;

import com.mjc.school.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ApplicationRunner applicationRunner = context.getBean(ApplicationRunner.class);
        applicationRunner.run();
//        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
//        NewsRepository newsRepository = context.getBean(NewsRepository.class);
//
//        System.out.println(authorRepository.readAll());
//
//        System.out.println();
//
//        System.out.println(newsRepository.readAll());

//        NewsController newsController = context.getBean(NewsController.class);
//
//        Scanner scanner = new Scanner(System.in);
//        Menu menu = new Menu(newsController);
//        boolean start = true;
//        while (start) {
//            menu.printMenu();
//            String userInput = scanner.next();
//            switch (userInput) {
//                case "1" -> menu.printAllNews();
//                case "2" -> menu.printNewsById(scanner);
//                case "3" -> menu.createNews(scanner);
//                case "4" -> menu.updateNews(scanner);
//                case "5" -> menu.removeNewsById(scanner);
//                case "6" -> start = false;
//                default -> System.out.println(userInput + " not found! Please make correct choice!");
//            }
//        }
    }
}
