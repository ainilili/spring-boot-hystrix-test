package org.smallnico.hystrix.command;

import org.aspectj.lang.ProceedingJoinPoint;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class RateLimitCommand extends HystrixCommand<Object>{

    private String uri;
    
    private ProceedingJoinPoint point;
    
    public RateLimitCommand(String uri, ProceedingJoinPoint point) {
        super(HystrixCommandGroupKey.Factory.asKey(uri));
        this.uri = uri;
    }

    @Override
    protected Object run() throws Exception {
        try {
            return point.proceed();
        } catch (Throwable e) {
            throw new Exception("Rate limit command happened exception." ,e);
        }
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public ProceedingJoinPoint getPoint() {
        return point;
    }

    public void setPoint(ProceedingJoinPoint point) {
        this.point = point;
    }

}
