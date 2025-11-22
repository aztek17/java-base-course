package ru.electronics.store.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.electronics.store.controller.DeviceController;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Before("Pointcuts.controllerPoint()")
    public void startArgs(JoinPoint point) {
        log.warn("Start call method:{} args:\n{}", point.getSignature().getName(), Arrays.toString(point.getArgs()));
    }

    @AfterReturning(pointcut = "Pointcuts.controllerPoint()", returning = "data")
    public void endCall(JoinPoint point, Object data) {
        log.warn("End call method:{} returned:\n{}",
                point.getSignature().getName(),
                data.toString().replaceAll(" ", "\n"));
    }

    @Around("Pointcuts.controllerPoint()")
    public Object timeCall(ProceedingJoinPoint point) {
        long timeStart = System.currentTimeMillis();
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new DeviceController(null);
        } finally {
            log.warn("Time call method:{} = {}ms",
                    point.getSignature().getName(),
                    System.currentTimeMillis() - timeStart);
        }
        return result;
    }

}
