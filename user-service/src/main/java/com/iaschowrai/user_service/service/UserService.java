package com.iaschowrai.user_service.service;

import com.iaschowrai.user_service.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long userId);
    UserDto updateUser(Long userId, UserDto userDto);
    void deleteUser(Long userId);
}
