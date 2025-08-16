package com.math012.order_service.business.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderResponseDTO {
    private String productName;
    private String productCategory;
    int totalQuantity;
    private double productPrice;
}