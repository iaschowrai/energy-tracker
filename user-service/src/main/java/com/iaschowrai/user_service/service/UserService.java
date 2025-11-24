package com.iaschowrai.user_service.service;

import com.iaschowrai.user_service.dto.UserDto;

import com.iaschowrai.user_service.entity.User;
import com.iaschowrai.user_service.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto input) {

        log.info("Creating users: {}", input);

        final User createdUser = User.builder()
                .name(input.getName())
                .email(input.getEmail())
                .address(input.getAddress())
                .alerting(input.isAlerting())
                .energyAlertingThreshold(input.getEnergyAlertingThreshold())
                .build();

        User saved =  userRepository.save(createdUser);

        return toDto(saved);
    }

    private UserDto toDto(User user) {

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .alerting(user.isAlerting())
                .energyAlertingThreshold(user.getEnergyAlertingThreshold())
                .build();
    }

    public UserDto getUserById(Long userId) {
        log.info("Get user by ID {}", userId);

        return userRepository.findById(userId)
                .map(this::toDto)
                .orElse(null);
    }

    public void updateUser(Long userId, UserDto userDto) {
        log.info("User id to update userDetails {} {}", userId, userDto);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setAlerting(userDto.isAlerting());
        user.setEnergyAlertingThreshold(userDto.getEnergyAlertingThreshold());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        log.info("User id to delete userDetails {} {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        userRepository.delete(user);
    }
}
