package com.iaschowrai.user_service.service.impl;

import com.iaschowrai.user_service.dto.UserDto;
import com.iaschowrai.user_service.entity.User;
import com.iaschowrai.user_service.exception.UserNotFoundException;
import com.iaschowrai.user_service.repository.UserRepository;
import com.iaschowrai.user_service.service.UserService;
import com.iaschowrai.user_service.utility.UserMapperUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapperUtility userMapperUtility;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapperUtility.toEntity(userDto);
        User saved = userRepository.save(user);
        return userMapperUtility.toDto(saved);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return userMapperUtility.toDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        userMapperUtility.updateEntityFromDto(existingUser, userDto);
        User updateUser = userRepository.save(existingUser);
        return userMapperUtility.toDto(updateUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.delete(user);
    }
}
