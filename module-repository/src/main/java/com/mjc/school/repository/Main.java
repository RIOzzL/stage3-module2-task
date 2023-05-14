package com.mjc.school.repository;

import com.mjc.school.repository.config.RepoConfig;
import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.repository.impl.NewsRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepoConfig.class);
        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        NewsRepository newsRepository = context.getBean(NewsRepository.class);

        System.out.println(authorRepository.readAll());

        System.out.println();

        System.out.println(newsRepository.readAll());

    }
}
