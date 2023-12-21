package com.my.teleport.system.client.service.application;

import com.my.teleport.system.client.service.domain.ClientDomainService;
import com.my.teleport.system.client.service.domain.ClientDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ClientDomainService agentDomainService() {
        return new ClientDomainServiceImpl();
    }


}
