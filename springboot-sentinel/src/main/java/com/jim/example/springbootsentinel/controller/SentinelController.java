package com.jim.example.springbootsentinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {
    @Autowired
    TestService testService;

    @GetMapping("/hello/{name}")
    public String sayHello(@PathVariable("name") String name){
        return testService.doTest(name);
    }
}
