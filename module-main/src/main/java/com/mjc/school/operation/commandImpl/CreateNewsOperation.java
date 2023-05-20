package com.mjc.school.operation.commandImpl;

import com.mjc.school.operation.Command;
import com.mjc.school.operation.NewsOperationsExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateNewsOperation implements Command {

    private final NewsOperationsExecutor operation;

    @Autowired
    public CreateNewsOperation(NewsOperationsExecutor operation) {
        this.operation = operation;
    }

    @Override
    public void execute() {
        operation.createNews();
    }
}