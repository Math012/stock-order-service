package com.math012.order_service.business.topics.kafka.producer;

import com.math012.order_service.business.DTO.OrderResponseDTO;
import com.math012.order_service.business.converter.ConvertToJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class OrderProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ConvertToJson converter;

    @Value("${spring.kafka.producer.order.requesting.product.topic}")
    private String requestingProductTopic;

    @Value("${spring.kafka.producer.order.send.order.for.payment.topic}")
    private String sendOrderForPayment;

    @Value("${spring.kafka.producer.order.send.order.payment.success.topic}")
    private String orderConfirmedPayment;

    @Value("${spring.kafka.producer.order.rollback.product.topic}")
    private String rollbackProduct;

    public void sendEventRequestingProduct(OrderResponseDTO dto) {
        try {
            log.info("sendEventRequestingProduct: Convertendo o objeto dto: {} para Json", dto);
            var content = converter.convertToJson(dto);
            log.info("sendEventRequestingProduct: DTO convertido com sucesso");
            log.info("sendEventRequestingProduct: enviando o tópico: {}", requestingProductTopic);
            kafkaTemplate.send(requestingProductTopic, content);
            log.info("sendEventRequestingProduct: Tópico: {} enviado com sucesso", requestingProductTopic);
        } catch (Exception e) {
            log.error("sendEventRequestingProduct: Erro ao enviar o tópico: {requestingProductTopic} erro: {}", e.getMessage());
        }
    }

    public void sendEventOrderForPayment(OrderResponseDTO dto) {
        try {
            log.info("sendEventOrderForPayment: Convertendo o objeto dto: {} para Json", dto);
            var content = converter.convertToJson(dto);
            log.info("sendEventOrderForPayment: DTO convertido com sucesso");
            log.info("sendEventOrderForPayment: enviando o tópico: {}", sendOrderForPayment);
            kafkaTemplate.send(sendOrderForPayment, content);
            log.info("sendEventOrderForPayment: Tópico: {} enviado com sucesso", sendOrderForPayment);
        } catch (Exception e) {
            log.error("sendEventOrderForPayment: Erro ao enviar o tópico: {sendOrderForPayment} erro: {}", e.getMessage());
        }
    }

    public void sendOrderConfirmedPayment(OrderResponseDTO dto) {
        try {
            log.info("sendOrderConfirmedPayment: Convertendo o objeto dto: {} para Json", dto);
            var content = converter.convertToJson(dto);
            log.info("sendOrderConfirmedPayment: DTO convertido com sucesso");
            log.info("sendOrderConfirmedPayment: enviando o tópico: {}", orderConfirmedPayment);
            kafkaTemplate.send(orderConfirmedPayment, content);
            log.info("sendOrderConfirmedPayment: Tópico: {} enviado com sucesso", orderConfirmedPayment);
        } catch (Exception e) {
            log.error("sendOrderConfirmedPayment: Erro ao enviar o tópico: {orderConfirmedPayment} erro: {}", e.getMessage());
        }
    }

    public void sendEventRollbackProduct(OrderResponseDTO dto) {
        try {
            log.info("sendEventRollbackProduct: Convertendo o objeto dto: {} para Json", dto);
            var content = converter.convertToJson(dto);
            log.info("sendEventRollbackProduct: DTO convertido com sucesso");
            log.info("sendEventRollbackProduct: enviando o tópico: {}", rollbackProduct);
            kafkaTemplate.send(rollbackProduct, content);
            log.info("sendEventRollbackProduct: Tópico: {} enviado com sucesso", rollbackProduct);
        } catch (Exception e) {
            log.error("sendEventRollbackProduct: Erro ao enviar o tópico: {rollbackProduct} erro: {}", e.getMessage());
        }
    }
}