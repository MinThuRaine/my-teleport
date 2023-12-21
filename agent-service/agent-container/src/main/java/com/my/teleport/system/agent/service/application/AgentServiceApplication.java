package com.my.teleport.system.agent.service.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.my.teleport.system.agent.service.dataaccess"})
@EntityScan(basePackages = {"com.my.teleport.system.agent.service.dataaccess"})
@SpringBootApplication( scanBasePackages = {"com.my.teleport.system"})
public class AgentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgentServiceApplication.class,args);
    }
}
