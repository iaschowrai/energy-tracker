package com.iaschowrai.user_service.service;

import com.iaschowrai.user_service.dto.UserDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserDto createUser(UserDto userDto) {

        log.info("Creating users: {}", userDto);
        return userDto;
    }
}
