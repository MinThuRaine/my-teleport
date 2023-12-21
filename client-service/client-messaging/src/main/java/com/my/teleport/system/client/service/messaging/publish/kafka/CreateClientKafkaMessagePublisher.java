package com.my.teleport.system.client.service.messaging.publish.kafka;

import com.my.teleport.system.client.service.domain.config.ClientServiceConfigData;
import com.my.teleport.system.client.service.domain.event.ClientCreateEvent;
import com.my.teleport.system.client.service.domain.ports.output.message.publisher.create.CreateClientMessagePublisher;
import com.my.teleport.system.client.service.messaging.mapper.ClientMessagingDataMapper;
import com.my.teleport.system.kafka.client.avro.model.ClientCreatedAvroModel;
import com.my.teleport.system.kafka.producer.KafkaMessageHelper;
import com.my.teleport.system.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateClientKafkaMessagePublisher implements CreateClientMessagePublisher {


    private final ClientMessagingDataMapper clientMessagingDataMapper;
    private final ClientServiceConfigData clientServiceConfigData;
    private final KafkaProducer<String, ClientCreatedAvroModel> kafkaProducer;
    private final KafkaMessageHelper kafkaMessageHelper;

    public CreateClientKafkaMessagePublisher(ClientMessagingDataMapper clientMessagingDataMapper,
                                             ClientServiceConfigData clientServiceConfigData,
                                             KafkaProducer<String, ClientCreatedAvroModel> kafkaProducer,
                                             KafkaMessageHelper kafkaMessageHelper) {
        this.clientMessagingDataMapper = clientMessagingDataMapper;
        this.clientServiceConfigData = clientServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }


    @Override
    public void publish(ClientCreateEvent domainEvent) {

        String clientId = domainEvent.getClient().getId().getValue();
        log.info("Agent CreatedEvent for AGENT id: {}", clientId);

        try {
            ClientCreatedAvroModel clientCreatedAvroModel = clientMessagingDataMapper
                    .createClientEventToCreateClientPublishAvroModel(domainEvent);

            kafkaProducer.send(clientServiceConfigData.getClientCreatedPublisherTopicName(),
                    clientId,
                    clientCreatedAvroModel,
                    kafkaMessageHelper
                            .getKafkaCallback(clientServiceConfigData.getClientCreatedPublisherTopicName(),
                                    clientCreatedAvroModel,
                                    clientId,
                                    "ClientCreatedAvroModel"));

            log.info("ClientCreatedAvroModel sent to Kafka for client id: {}", clientCreatedAvroModel.getClientId());
        } catch (Exception e) {
            log.error("Error while sending ClientCreatedAvroModel message" +
                    " to kafka with client id: {}, error: {}", clientId, e.getMessage());
        }


    }
}
