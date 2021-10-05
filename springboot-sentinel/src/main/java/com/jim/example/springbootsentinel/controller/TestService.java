package com.jim.example.springbootsentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @SentinelResource(value = "doTest",blockHandler="blockHandler",fallback = "blockHandler") //声明限流的资源
    public String doTest(String name){
        return "hello , "+name;
    }
    public String blockHandler(String name, BlockException e){ //降级，限流触发的
       return "被限流了";
    }
    public String fallback(String name){ //熔断触发的
        return "被降级了";
    }

}