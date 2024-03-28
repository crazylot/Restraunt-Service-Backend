package com.microservices.swiggy.restrauntservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.microservices.swiggy.restrauntservice.event.OrderPlacedEvent;


@Component
@RequiredArgsConstructor
@Slf4j
public class OrderPlacedEventListener {

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @EventListener
    public void handleOrderPlacedEvent(OrderPlacedEvent event) {
        log.info("Order Placed Event Received, Sending OrderPlacedEvent to notificationTopic: {}", event.getOrderNumber());

        // Create Observation for Kafka Template
        try {
        	kafkaTemplate.send("notificationTopic",
                        new OrderPlacedEvent(event.getOrderNumber()));
               
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error while sending message to Kafka", e);
        }
    }
}
