package com.iaschowrai.user_service.utility;

import com.iaschowrai.user_service.dto.UserDto;
import com.iaschowrai.user_service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperUtility {

    public UserDto toDto(User user) {
        if (user == null) return null;

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .alerting(user.isAlerting())
                .energyAlertingThreshold(user.getEnergyAlertingThreshold())
                .build();
    }
    public User toEntity(UserDto dto) {
        if (dto == null) return null;

        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .alerting(dto.isAlerting())
                .energyAlertingThreshold(dto.getEnergyAlertingThreshold())
                .build();
    }

    public void updateEntityFromDto (User user, UserDto dto) {
        if (dto == null || user == null) return;

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAddress(dto.getAddress());
        user.setAlerting(dto.isAlerting());
        user.setEnergyAlertingThreshold(dto.getEnergyAlertingThreshold());
    }
}
