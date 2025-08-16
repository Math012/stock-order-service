package com.math012.order_service.infra.client;

import com.math012.order_service.business.DTO.UserResponseDTO;
import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user", url="${user.url}")
public interface UserClient {
    @GetMapping("/{email}")
    UserResponseDTO findByEmail(@PathVariable("email")String email,@RequestHeader("Authorization") String token);
}