package com.my.teleport.system.client.service.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.my.teleport.system.client.service.dataaccess"})
@EntityScan(basePackages = {"com.my.teleport.system.client.service.dataaccess"})
@SpringBootApplication(scanBasePackages = {"com.my.teleport.system"})
public class ClientServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }
}
