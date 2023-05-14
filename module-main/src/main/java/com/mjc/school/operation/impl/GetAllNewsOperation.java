package com.mjc.school.operation.impl;

import com.mjc.school.operation.Operations;
import com.mjc.school.utils.Menu;
import org.springframework.beans.factory.annotation.Autowired;

public class GetAllNewsOperation implements Operations {

    private Menu menu;

    @Autowired
    public GetAllNewsOperation(Menu menu) {
        this.menu = menu;
    }


    @Override
    public void execute() {
        menu.printAllNews();
    }
}
