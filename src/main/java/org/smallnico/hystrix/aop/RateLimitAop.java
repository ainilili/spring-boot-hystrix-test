package org.smallnico.hystrix.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smallnico.hystrix.command.RateLimitCommand;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import rx.Observable;



@Aspect
@Order(1)
@Component
public class RateLimitAop {
    
	private final static Logger LOGGER = LoggerFactory.getLogger(RateLimitAop.class);

	@Pointcut("execution(* org.smallnico.hystrix.controller..*(..))")
	public void point(){}
	
	@Around(value="point()")
	public Object aroundMethod(ProceedingJoinPoint point){
		try {
		    Observable<Object> result = new RateLimitCommand(point.getKind(), point).observe();
		    
		    LOGGER.info("Before executing !!");
            return point.proceed();
        } catch (Throwable e) {
            return null;
        }
	}

}
