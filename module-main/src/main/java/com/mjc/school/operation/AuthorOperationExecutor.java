package com.mjc.school.operation;

import com.mjc.school.controller.impl.AuthorController;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.exception.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.mjc.school.service.exception.ServiceError.VALIDATE_INT_VALUE;
import static com.mjc.school.utils.Constants.*;
import static com.mjc.school.utils.Operations.*;

@Component
public class AuthorOperationExecutor {

    private final AuthorController authorController;
    private final Scanner scanner;

    @Autowired
    public AuthorOperationExecutor(AuthorController authorController) {
        this.authorController = authorController;
        this.scanner = new Scanner(System.in);
    }

    public void printAllAuthors() {
        System.out.println(authorController.readAll());
    }

    public void getAuthorById() {
        System.out.println(OPERATION + GET_AUTHOR_BY_ID.getOperationDescription());
        System.out.println(ENTER_AUTHOR_ID);
        try {
            Long id = validateNumberInput(scanner, AUTHOR_ID);
            System.out.println(authorController.readById(id));
        } catch (ValidatorException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void createAuthor() {
        System.out.println(OPERATION + CREATE_AUTHOR.getOperationDescription());
        System.out.println(ENTER_AUTHOR_NAME);
        AuthorDto authorDto = new AuthorDto();
        try {
            authorDto.setName(scanner.next());
            System.out.println(authorController.create(authorDto));
        } catch (ValidatorException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void updateAuthor() {
        System.out.println(OPERATION + UPDATE_AUTHOR.getOperationDescription());
        System.out.println(ENTER_AUTHOR_NAME);
        AuthorDto authorDto = new AuthorDto();
        try {
            scanner.nextLine();
            String authorName = scanner.nextLine();
            authorDto.setName(authorName);
            System.out.println(ENTER_AUTHOR_ID);
            authorDto.setId(validateNumberInput(scanner, AUTHOR_ID));
            System.out.println(authorController.update(authorDto));
        } catch (ValidatorException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void deleteAuthorById() {
        System.out.println(OPERATION + REMOVE_AUTHOR_BY_ID.getOperationDescription());
        System.out.println(ENTER_AUTHOR_ID);
        try {
            long id = validateNumberInput(scanner, AUTHOR_ID);
            System.out.println(authorController.deleteById(id));
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
