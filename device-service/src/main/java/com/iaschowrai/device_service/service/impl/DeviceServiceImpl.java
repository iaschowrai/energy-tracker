package com.iaschowrai.device_service.service.impl;

import com.iaschowrai.device_service.dto.DeviceDto;
import com.iaschowrai.device_service.entity.Device;
import com.iaschowrai.device_service.exception.DeviceNotFoundException;
import com.iaschowrai.device_service.repository.DeviceRepository;
import com.iaschowrai.device_service.service.DeviceService;
import com.iaschowrai.device_service.utility.DeviceMapperUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private DeviceRepository deviceRepository;

    private DeviceMapperUtility deviceMapperUtility;

    public DeviceServiceImpl(DeviceRepository deviceRepository){
        this.deviceRepository = deviceRepository;
    }

    @Override
    public DeviceDto createDevice(DeviceDto deviceDto) {
        Device device = deviceMapperUtility.toEntity(deviceDto);  // convert dto to device to save into database
        Device saved = deviceRepository.save(device);             // save device data to database
        return deviceMapperUtility.toDto(saved);                  // return the saved dto back to the user
    }


    @Override
    public DeviceDto getDeviceById(Long deviceId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(deviceId));
        return deviceMapperUtility.toDto(device);
    }

    @Override
    public DeviceDto updateDeviceById(Long id, DeviceDto deviceDto) {
        Device existingDevice = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));
        deviceMapperUtility.updateEntityFromDto(existingDevice, deviceDto);
        Device updateDevice = deviceRepository.save(existingDevice);
        return deviceMapperUtility.toDto(updateDevice);
    }

    @Override
    public void deleteDevice(Long id) {
        Device existingDevice = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(id));
        deviceRepository.delete(existingDevice);
    }


}
