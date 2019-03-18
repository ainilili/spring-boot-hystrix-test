package org.smallnico.hystrix.controller.test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.github.kevinsawicki.http.HttpRequest;

public class TestControllerTest {

    static final ThreadPoolExecutor POOLS = new ThreadPoolExecutor(100, 100, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
    
    @Test
    public void test() throws InterruptedException {
        for(int i = 0; i < 11; i ++) {
            POOLS.execute(() -> {
               System.out.println(HttpRequest.get("http://localhost:8081/api/seconds/20").body());
            });
        }
        POOLS.awaitTermination(100, TimeUnit.SECONDS);
    }
    
}
