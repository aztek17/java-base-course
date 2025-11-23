package ru.electronics.store.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerPoint() {
    }
}
