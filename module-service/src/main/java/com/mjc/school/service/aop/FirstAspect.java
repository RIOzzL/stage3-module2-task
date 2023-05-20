package com.mjc.school.service.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FirstAspect {

    // @within - check annotation on the class layer
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {

    }

    // within - check class type name
    @Pointcut("within(com.mjc.school.service..*Service)")
    public void isServiceLayer() {

    }

    // this - check aop proxy class type
    // target - check object class type
    @Pointcut("this(org.springframework.stereotype.Repository)")
    //@Pointcut("target(org.springframework.stereotype.Repository)")
    public void isRepositoryLayer() {

    }

    // annotation - check annotation on method level
    @Pointcut("isControllerLayer() && @annotation(org.springframework.stereotype.Component)")
    public void hasComponentAnnotation() {

    }

    // args - check method param type
    // * - any param type
    // .. - 0+ any param types
    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelParam() {

    }

    // args - check annotation on the param type in Method
    // * - any param type
    // .. - 0+ any param types
//    @Pointcut("isControllerLayer() && @args(com.mjc.school.service.impl.AuthorService,..)")
//    public void hasUserInfoParamAnnotation() {
//
//    }


    // bean - check bean name
    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {

    }

    // execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?) - check bean name
    @Pointcut("execution(public Long com.mjc.school.service.*.findById(Long))")
    public void anyFindByIdServiceMethod() {

    }

    // @Before("execution(public Long com.mjc.school.service.*.findById(Long))")
    @Before("anyFindByIdServiceMethod() && args(id)")
    public void addLogging(JoinPoint joinPoint, Long id) {
        System.out.println("invoked findById method");
    }
}
