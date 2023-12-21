package com.my.teleport.system.order.service.messaging.kafka;

import com.my.teleport.system.kafka.order.avro.model.OrderProcessingAvroModel;
import com.my.teleport.system.order.service.domain.order.event.OrderProcessEvent;
import com.my.teleport.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import com.my.teleport.system.kafka.producer.KafkaMessageHelper;
import com.my.teleport.system.kafka.producer.service.KafkaProducer;
import com.my.teleport.system.order.service.domain.order.config.OrderServiceConfigData;
import com.my.teleport.system.order.service.domain.order.ports.output.message.publisher.create.ProcessOrderMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderProcessKafkaMessagePublisher implements ProcessOrderMessagePublisher {


    private final OrderMessagingDataMapper orderMessagingDataMapper;
    private final OrderServiceConfigData orderServiceConfigData;
    private final KafkaProducer<String, OrderProcessingAvroModel> kafkaProducer;
    private final KafkaMessageHelper kafkaMessageHelper;

    public OrderProcessKafkaMessagePublisher(OrderMessagingDataMapper orderMessagingDataMapper,
                                             OrderServiceConfigData orderServiceConfigData,
                                             KafkaProducer<String, OrderProcessingAvroModel> kafkaProducer,
                                             KafkaMessageHelper kafkaMessageHelper) {
        this.orderMessagingDataMapper = orderMessagingDataMapper;
        this.orderServiceConfigData = orderServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }


    @Override
    public void publish(OrderProcessEvent domainEvent) {

        String orderId = domainEvent.getOrder().getId().getValue();
        log.info("Order ProcessEvent for order id: {}", orderId);

        try {
            OrderProcessingAvroModel orderProcessingAvroModel = orderMessagingDataMapper
                    .processOrderEventToOrderProcessingAvroModel(domainEvent);

            kafkaProducer.send(orderServiceConfigData.getOrderProcessedPublisherTopicName(),
                    orderId,
                    orderProcessingAvroModel,
                    kafkaMessageHelper
                            .getKafkaCallback(orderServiceConfigData.getOrderProcessedPublisherTopicName(),
                                    orderProcessingAvroModel,
                                    orderId,
                                    "OrderProcessingAvroModel"));

            log.info("OrderProcessingAvroModel sent to Kafka for order id: {}", orderProcessingAvroModel.getOrderId());
        } catch (Exception e) {
            log.error("Error while sending OrderProcessingAvroModel message" +
                    " to kafka with order id: {}, error: {}", orderId, e.getMessage());
        }


    }
}
