package com.iaschowrai.alert_service.repository;

import com.iaschowrai.alert_service.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AlertRepository extends JpaRepository<Alert, Long> {
}
