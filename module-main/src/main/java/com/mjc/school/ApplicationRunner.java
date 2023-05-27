package com.mjc.school;

import com.mjc.school.controller.operation.MenuCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ApplicationRunner {

    private final MenuCommands menu;

    @Autowired
    public ApplicationRunner(MenuCommands menu) {
        this.menu = menu;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean start = true;
        while (start) {
            menu.printMenu();
            String userInput = scanner.next();
            switch (userInput) {
                case "1" -> menu.printAllNews();
                case "2" -> menu.printAllAuthors();
                case "3" -> menu.printNewsByID();
                case "4" -> menu.printAuthorById();
                case "5" -> menu.createNews();
                case "6" -> menu.createAuthor();
                case "7" -> menu.updateNews();
                case "8" -> menu.updateAuthor();
                case "9" -> menu.deleteNewsByID();
                case "10" -> menu.deleteAuthorByID();
                case "11" -> start = false;
                default -> System.out.println(userInput + " not found! Please make correct choice!");
            }
        }
    }
}
