package com.mjc.school.operation.commandImpl;

import com.mjc.school.operation.AuthorOperationExecutor;
import com.mjc.school.operation.Command;
import org.springframework.stereotype.Component;

@Component
public class CreateAuthorOperation implements Command {

    private final AuthorOperationExecutor operation;

    public CreateAuthorOperation(AuthorOperationExecutor operation) {
        this.operation = operation;
    }


    @Override
    public void execute() {
        operation.createAuthor();
    }
}
