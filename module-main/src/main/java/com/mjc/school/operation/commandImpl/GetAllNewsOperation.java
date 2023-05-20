package com.mjc.school.operation.commandImpl;

import com.mjc.school.operation.Command;
import com.mjc.school.operation.NewsOperationsExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAllNewsOperation implements Command {

    private NewsOperationsExecutor operation;

    @Autowired
    public GetAllNewsOperation(NewsOperationsExecutor operation) {
        this.operation = operation;
    }


    @Override
    public void execute() {
        operation.printAllNews();
    }
}
