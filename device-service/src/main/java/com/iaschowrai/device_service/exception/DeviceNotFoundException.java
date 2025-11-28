package com.iaschowrai.device_service.exception;

public class DeviceNotFoundException extends RuntimeException {
    public DeviceNotFoundException(Long deviceId) {
        super("Device not found with device id: " +deviceId);
    }
}
