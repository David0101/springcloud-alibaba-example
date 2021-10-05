package com.jim.example.springbootsentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class SpringbootSentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootSentinelApplication.class, args);
    }

//    @PostConstruct
//    public void init() {
//        List<FlowRule> rules = new ArrayList<>();
//        FlowRule flowRule = new FlowRule();
//        flowRule.setResource("doTest"); //针对那个资源设置规则
//        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);//QPS或者并发数
//        flowRule.setCount(2); //QPS=5
//        rules.add(flowRule);
//        FlowRuleManager.loadRules(rules);
//    }
}
