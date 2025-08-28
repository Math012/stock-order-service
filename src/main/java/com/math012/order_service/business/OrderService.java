package com.math012.order_service.business;


import com.math012.order_service.business.DTO.OrderResponseDTO;
import com.math012.order_service.business.topics.kafka.producer.OrderProducer;
import com.math012.order_service.infra.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private JwtUtil jwtUtil;

    public OrderResponseDTO sendEventRequestingProduct(OrderResponseDTO dto, String token){
        log.info("sendEventRequestingProductService: ");
        dto.setUserEmail(jwtUtil.extractEmail(token.substring(7)));
        orderProducer.sendEventRequestingProduct(dto);
        return dto;
    }
}