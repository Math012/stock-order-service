package com.math012.order_service.business.topics.kafka.consumer;

import com.math012.order_service.business.DTO.OrderResponseDTO;
import com.math012.order_service.business.converter.ConvertToJson;
import com.math012.order_service.business.topics.kafka.producer.OrderProducer;
import com.math012.order_service.infra.exception.ProductIsNotInStockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderConsumer {

    @Autowired
    OrderProducer producer;

    @Autowired
    ConvertToJson converter;

    @KafkaListener(
            topics = "${spring.kafka.consumer.product.has.stock.topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void productHasInventory(String order){
        log.info("productHasInventory: Consumindo o tópico product.has.stock.topic");
        OrderResponseDTO dto = converter.readJson(order, OrderResponseDTO.class);
        producer.sendEventOrderForPayment(dto);
    }

    @KafkaListener(
            topics = "${spring.kafka.consumer.product.out.of.stock.topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void productNotStock(String order){
        log.info("productNotStock: Consumindo o tópico product.out.of.stock.topic");
        throw new ProductIsNotInStockException("Error when requesting product: "+order+", the product has no stock");
    }

    @KafkaListener(
            topics = "${spring.kafka.consumer.payment.success.topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void confirmedPaymentSuccess(String order){
        log.info("confirmedPaymentSuccess: Consumindo o tópico payment.success.topic");
        OrderResponseDTO dto = converter.readJson(order, OrderResponseDTO.class);
        System.out.println("payment success consumido pelo Order");
        producer.sendOrderConfirmedPayment(dto);
    }

    @KafkaListener(
            topics = "${spring.kafka.consumer.payment.failed.topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void rollbackProduct(String order){
        log.info("rollbackProduct: Consumindo o tópico payment.failed.topic");
        OrderResponseDTO dto = converter.readJson(order, OrderResponseDTO.class);
        producer.sendEventRollbackProduct(dto);
    }
}