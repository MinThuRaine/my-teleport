package com.my.teleport.system.agent.service.messaging.publisher.kafka;

import com.my.teleport.system.agent.service.domain.config.AgentServiceConfigData;
import com.my.teleport.system.agent.service.domain.event.AgentCreateEvent;
import com.my.teleport.system.agent.service.domain.ports.output.message.publisher.create.CreateAgentMessagePublisher;
import com.my.teleport.system.agent.service.messaging.mapper.AgentMessagingDataMapper;
import com.my.teleport.system.kafka.agent.avro.model.AgentCreatedAvroModel;
import com.my.teleport.system.kafka.producer.KafkaMessageHelper;
import com.my.teleport.system.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateAgentKafkaMessagePublisher implements CreateAgentMessagePublisher {



    private final AgentMessagingDataMapper agentMessagingDataMapper;
    private final AgentServiceConfigData agentServiceConfigData;
    private final KafkaProducer<String, AgentCreatedAvroModel> kafkaProducer;
    private final KafkaMessageHelper agentKafkaMessageHelper;

    public CreateAgentKafkaMessagePublisher(AgentMessagingDataMapper agentMessagingDataMapper,
                                            AgentServiceConfigData agentServiceConfigData,
                                            KafkaProducer<String, AgentCreatedAvroModel> kafkaProducer,
                                            KafkaMessageHelper kafkaMessageHelper) {
        this.agentMessagingDataMapper = agentMessagingDataMapper;
        this.agentServiceConfigData = agentServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.agentKafkaMessageHelper = kafkaMessageHelper;
    }



    @Override
    public void publish(AgentCreateEvent domainEvent) {

        String agentId = domainEvent.getAgent().getId().getValue().toString();
        log.info("Agent CreatedEvent for AGENT id: {}", agentId);

        try {
            AgentCreatedAvroModel agentCreatedAvroModel = agentMessagingDataMapper
                    .createAgentEventToCreateAgentPublishAvroModel(domainEvent);

            kafkaProducer.send(agentServiceConfigData.getAgentCreatedPublisherTopicName(),
                    agentId,
                    agentCreatedAvroModel,
                    agentKafkaMessageHelper
                            .getKafkaCallback(agentServiceConfigData.getAgentCreatedPublisherTopicName(),
                                    agentCreatedAvroModel,
                                    agentId,
                                    "AgentCreatedAvroModel"));

            log.info("AgentCreatedAvroModel sent to Kafka for agent id: {}", agentCreatedAvroModel.getAgentId() );
        } catch (Exception e) {
            log.error("Error while sending AgentCreatedAvroModel message" +
                    " to kafka with agent id: {}, error: {}", agentId, e.getMessage());
        }


    }
}
