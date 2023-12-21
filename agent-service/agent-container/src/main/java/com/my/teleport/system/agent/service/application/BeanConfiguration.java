package com.my.teleport.system.agent.service.application;

import com.my.teleport.system.agent.service.domain.AgentDomainService;
import com.my.teleport.system.agent.service.domain.AgentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AgentDomainService agentDomainService(){
        return new AgentDomainServiceImpl();
    }



}
