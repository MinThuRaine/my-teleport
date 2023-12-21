package com.my.teleport.system.agent.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "agent-service")
public class AgentServiceConfigData {
    private String agentCreatedPublisherTopicName;


}