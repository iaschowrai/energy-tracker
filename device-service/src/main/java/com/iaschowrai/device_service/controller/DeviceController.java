package com.iaschowrai.device_service.controller;

import com.iaschowrai.device_service.dto.DeviceDto;
import com.iaschowrai.device_service.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/device")
@RequiredArgsConstructor
public class DeviceController {

    private DeviceService deviceService;

    @PostMapping("/create")
    public ResponseEntity<DeviceDto> createDevice(@RequestBody DeviceDto deviceDto){
        DeviceDto deviceCreated = deviceService.createDevice(deviceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(deviceCreated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceDto> getDeviceById(@PathVariable Long Id){
        DeviceDto deviceDto = deviceService.getDeviceById(Id);
        return ResponseEntity.ok(deviceDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceDto> updateDeviceById(@PathVariable Long id, @RequestBody DeviceDto deviceDto){
        DeviceDto DeviceUpdated = deviceService.updateDeviceById(id, deviceDto);
        return ResponseEntity.ok(DeviceUpdated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeviceById(@PathVariable Long id){
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
