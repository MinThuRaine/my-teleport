package com.my.teleport.system.order.service.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.my.teleport.system.taskorder.service.dataaccess"})
@EntityScan(basePackages = {"com.my.teleport.system.taskorder.service.dataaccess"})
@SpringBootApplication(scanBasePackages = {"com.my.teleport.system"})
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
