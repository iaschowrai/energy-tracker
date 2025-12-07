package com.iaschowrai.ingestion_service.service;

import com.iaschowrai.ingestion_service.dto.EnergyUsageDto;

public interface IngestionService {
    void ingestionEnergyUsage(EnergyUsageDto energyUsageDto);
}
