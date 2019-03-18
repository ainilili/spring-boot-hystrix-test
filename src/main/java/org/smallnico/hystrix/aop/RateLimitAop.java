package org.smallnico.hystrix.aop;


import java.util.concurrent.Future;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smallnico.hystrix.command.RateLimitCommand;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Aspect
@Order(1)
@Component
public class RateLimitAop {
    
	private final static Logger LOGGER = LoggerFactory.getLogger(RateLimitAop.class);

	@Pointcut("execution(* org.smallnico.hystrix.controller..*(..))")
	public void point(){}
	
	@Around(value="point()")
	public Object aroundMethod(final ProceedingJoinPoint point){
		try {
		    Future<Object> obj = new RateLimitCommand("abc", point).queue();
		    LOGGER.info("Before executing !!");
            return obj.get();
        } catch (Throwable e) {
            e.printStackTrace();
            return e.getMessage();
        }
	}

}
