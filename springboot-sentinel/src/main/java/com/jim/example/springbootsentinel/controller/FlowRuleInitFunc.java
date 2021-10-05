package com.jim.example.springbootsentinel.controller;

import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientAssignConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfig;
import com.alibaba.csp.sentinel.cluster.client.config.ClusterClientConfigManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;


import java.util.ArrayList;
import java.util.List;

public class FlowRuleInitFunc implements InitFunc{
    private final String nacosAddress="192.168.216.128:8848";
    private final String groupId="SENTINEL_GROUP";
    private final String dataId="-flow-rules";

    private final String clusterServerHost="localhost";
    private final int clusterServerPort=9999;
    private final int requestTimeOut=20000;
    private final String appName="App-Test";

    @Override
    public void init() throws Exception {
        loadClusterConfig();
        registerFlowRule();
    }
    private void loadClusterConfig(){
        ClusterClientAssignConfig assignConfig=new ClusterClientAssignConfig();
        assignConfig.setServerHost(clusterServerHost); //放到配置中心
        assignConfig.setServerPort(clusterServerPort);
        ClusterClientConfigManager.applyNewAssignConfig(assignConfig);
        ClusterClientConfig clientConfig=new ClusterClientConfig();
        clientConfig.setRequestTimeout(requestTimeOut);  //放到配置中心
        ClusterClientConfigManager.applyNewConfig(clientConfig);
    }

    private void registerFlowRule(){
        ReadableDataSource<String,List<FlowRule>> flowRuleDs=
                new NacosDataSource<List<FlowRule>>(nacosAddress,groupId,appName+dataId,
                        source-> JSON.parseObject(source,new TypeReference<List<FlowRule>>(){}));
        FlowRuleManager.register2Property(flowRuleDs.getProperty());
    }
}
