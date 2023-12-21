package com.my.teleport.system.client.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "client-service")
public class ClientServiceConfigData {
    private String clientCreatedPublisherTopicName;


}