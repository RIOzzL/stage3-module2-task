package com.mjc.school.operation.commandImpl;

import com.mjc.school.operation.AuthorOperationExecutor;
import com.mjc.school.operation.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteAuthorByIdOperation implements Command {

    private final AuthorOperationExecutor operation;

    @Autowired
    public DeleteAuthorByIdOperation(AuthorOperationExecutor operation) {
        this.operation = operation;
    }

    @Override
    public void execute() {
        operation.deleteAuthorById();
    }
}
