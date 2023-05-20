package com.mjc.school.operation.commandImpl;

import com.mjc.school.operation.AuthorOperationExecutor;
import com.mjc.school.operation.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllAuthorsOperation implements Command {

    private AuthorOperationExecutor operation;

    @Autowired
    public GetAllAuthorsOperation(AuthorOperationExecutor operation) {
        this.operation = operation;
    }

    @Override
    public void execute() {
        System.out.println("Execute from - GetAllAuthorsOperation!!!");
        operation.printAllAuthors();
    }
}
