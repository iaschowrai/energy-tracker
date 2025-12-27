package com.iaschowrai.ingestion_service.service.impl;

import com.iaschowrai.ingestion_service.dto.EnergyUsageDto;
import com.iaschowrai.kafka.event.EnergyUsageEvent;
import com.iaschowrai.ingestion_service.service.IngestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class IngestionServiceImpl implements IngestionService {

    private final Logger log = LoggerFactory.getLogger(IngestionServiceImpl.class);

    private final KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate;

    public IngestionServiceImpl(KafkaTemplate<String, EnergyUsageEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void ingestionEnergyUsage(EnergyUsageDto input) {
        // convert DTO to Event
        EnergyUsageEvent event = EnergyUsageEvent.builder()
                .deviceId(input.deviceId())
                .energyConsumed(input.energyConsumed())
                .timestamp(input.timestamp())
                .build();

    // send to kafka topics
    kafkaTemplate.send("energy-usage",event);
    log.info("Ingested Energy Usage Event: {}" , event);
    }
}
