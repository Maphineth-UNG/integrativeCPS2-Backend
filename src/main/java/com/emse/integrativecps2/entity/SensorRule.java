package com.emse.integrativecps2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sensor_rules")
public class SensorRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String triggerType; // e.g., "temperature", "humidity"
    private String condition; // ">", "<", "=="
    private double threshold;
}
