package com.math012.order_service.controller;

import com.math012.order_service.business.DTO.OrderResponseDTO;
import com.math012.order_service.business.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class OrderController {

    private final OrderService service;

        @PostMapping("/send")
    public ResponseEntity<OrderResponseDTO> sendEventRequestingProduct(@RequestBody OrderResponseDTO dto, @RequestHeader("Authorization")String token){
            return ResponseEntity.ok(service.sendEventRequestingProduct(dto,token));
    }
}