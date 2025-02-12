package com.emse.integrativecps2.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "weather_rules")
public class WeatherRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;  // Email to send alerts
    private String triggerType; // e.g., temperature, humidity
    private String condition; // >, <, ==
    private double threshold; // Setpoint value
}
