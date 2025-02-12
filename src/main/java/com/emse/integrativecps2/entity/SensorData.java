package com.emse.integrativecps2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sensor_data")
@Data
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double temperature;
    private double humidity;
    private double airQuality;
}
