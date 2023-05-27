package com.mjc.school.controller.operation;

import com.mjc.school.controller.operation.commandImpl.*;
import com.mjc.school.controller.utils.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuCommands {

    private List<Command> commands;

    @Autowired
    public MenuCommands(List<Command> commands) {
        this.commands = commands;
    }

    public void printMenu() {
        System.out.println("Enter the number of operation:");
        for (Operations value : Operations.values()) {
            System.out.println(value.getOperationNumber() + " - " + value.getOperationDescription());
        }
    }

    public void printAllNews() {
        commands.stream().filter(command -> command instanceof GetAllNewsOperation)
                .findFirst().get()
                .execute();
    }

    public void printAllAuthors() {
        commands.stream().filter(command -> command instanceof GetAllAuthorsOperation)
                .findFirst().get()
                .execute();
    }

    public void printNewsByID() {
        commands.stream().filter(command -> command instanceof GetNewsByIdOperation)
                .findFirst().get()
                .execute();
    }

    public void printAuthorById() {
        commands.stream().filter(command -> command instanceof GetAuthorByIdOperation)
                .findFirst().get()
                .execute();
    }

    public void createNews() {
        commands.stream().filter(command -> command instanceof CreateNewsOperation)
                .findFirst().get()
                .execute();
    }

    public void createAuthor() {
        commands.stream().filter(command -> command instanceof CreateAuthorOperation)
                .findFirst().get()
                .execute();
    }

    public void updateNews() {
        commands.stream().filter(command -> command instanceof UpdateNewsOperation)
                .findFirst().get()
                .execute();
    }

    public void updateAuthor() {
        commands.stream().filter(command -> command instanceof UpdateAuthorOperation)
                .findFirst().get()
                .execute();
    }

    public void deleteNewsByID() {
        commands.stream().filter(command -> command instanceof DeleteNewsByIdOperation)
                .findFirst().get()
                .execute();
    }

    public void deleteAuthorByID() {
        commands.stream().filter(command -> command instanceof DeleteAuthorByIdOperation)
                .findFirst().get()
                .execute();
    }
}