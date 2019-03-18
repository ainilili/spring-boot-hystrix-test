package org.smallnico.hystrix.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/seconds/{value}")
    public String seconds(@PathVariable int value) throws InterruptedException {
        Thread.sleep(value * 1000l);
        return "success";
    }
    
}
