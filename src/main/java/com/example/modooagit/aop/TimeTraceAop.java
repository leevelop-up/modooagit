package com.example.modooagit.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
    @Around("execution(* com.example.modooagit..*(..))")
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable{

        long start = System.currentTimeMillis();
        System.out.println("start:"+joinPoint.toString());
        try {
            return joinPoint.proceed();
        }finally {
            long end = System.currentTimeMillis();
            long time = end - start;
            System.out.println("end="+joinPoint.toString()+" "+ time+"ms");
        }


    }
}
