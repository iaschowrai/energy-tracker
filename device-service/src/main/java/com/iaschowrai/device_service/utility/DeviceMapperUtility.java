package com.iaschowrai.device_service.utility;

import com.iaschowrai.device_service.dto.DeviceDto;
import com.iaschowrai.device_service.entity.Device;

public class DeviceMapperUtility {

    public DeviceDto toDto(Device device) {
        if(device == null) return null;

        return DeviceDto.builder()
                .id(device.getId())
                .name(device.getName())
                .type(device.getType())
                .location(device.getLocation())
                .userId(device.getUserId())
                .build();
    }


    public Device toEntity(DeviceDto deviceDto) {
        if(deviceDto == null) return null;

        return Device.builder()
                .id(deviceDto.getId())
                .name(deviceDto.getName())
                .type(deviceDto.getType())
                .location(deviceDto.getLocation())
                .userId(deviceDto.getUserId())
                .build();

    }

    public void updateEntityFromDto(Device existingDevice, DeviceDto deviceDto) {
        if(existingDevice == null || deviceDto == null) return;

        existingDevice.setName(deviceDto.getName());
        existingDevice.setType(deviceDto.getType());
        existingDevice.setLocation(deviceDto.getLocation());
        existingDevice.setUserId(deviceDto.getUserId());
    }
}
