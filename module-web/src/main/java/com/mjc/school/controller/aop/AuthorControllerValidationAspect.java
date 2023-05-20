package com.mjc.school.controller.aop;

import com.mjc.school.controller.dto.AuthorRequestDto;

import com.mjc.school.controller.validation.validator.AuthorDtoValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuthorControllerValidationAspect {

    @Autowired
    private AuthorDtoValidator authorDtoValidator;

    @Pointcut("within(com.mjc.school.controller.impl.AuthorController)")
    public void isAuthorController() {

    }

    @Pointcut("isAuthorController() && @annotation(com.mjc.school.controller.validation.restriction.UpdateValid)")
    public void hasUpdateValidAnnotation() {

    }

    @Before("hasUpdateValidAnnotation()")
    public void updateValidProcessor(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        authorDtoValidator.updateValidation((AuthorRequestDto) args[0]);
    }

    @Pointcut("isAuthorController() && @annotation(com.mjc.school.controller.validation.restriction.CreateValid)")
    public void hasCreateValidAnnotation() {

    }

    @Before("hasCreateValidAnnotation()")
    public void createValidProcessor(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        authorDtoValidator.createValidation((AuthorRequestDto) args[0]);
    }

    @Pointcut("isAuthorController() && @args(com.mjc.school.controller.validation.restriction.IsEntityExist,..)")
    public void hasIsEntityExistAnnotation() {

    }

    @Before("hasIsEntityExistAnnotation() && args(id)")
    public void IsEntityExistAnnotationProcessor(JoinPoint joinPoint, Long id) {
        authorDtoValidator.isExistValidation(id);
    }
}
