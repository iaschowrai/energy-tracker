package com.iaschowrai.device_service.service;

import com.iaschowrai.device_service.dto.DeviceDto;

public interface DeviceService {

    DeviceDto createDevice(DeviceDto deviceDto);

    DeviceDto getDeviceById(Long deviceId);

    DeviceDto updateDeviceById(Long id, DeviceDto deviceDto);

    void deleteDevice(Long id);
}
