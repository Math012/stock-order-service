package com.math012.order_service.business.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserResponseDTO {
    private Long id;
    private String email;
    private String password;
}