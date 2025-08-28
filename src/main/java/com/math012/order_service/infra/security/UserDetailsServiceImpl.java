package com.math012.order_service.infra.security;

import com.math012.order_service.business.DTO.UserResponseDTO;
import com.math012.order_service.infra.client.UserClient;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl {

    private final UserClient client;

    public UserDetails loadUserByUsernameCustom(String email, String token) throws UsernameNotFoundException{
        UserResponseDTO userResponseDTO =  client.findByEmail(email,token);
        return User
                .withUsername(userResponseDTO.getEmail())
                .password(userResponseDTO.getPassword())
                .build();
    }
}