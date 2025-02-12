package com.emse.integrativecps2.model;

import lombok.Data;

@Data
public class SensorDto {
    private double temperature;
    private double humidity;
    private double airQuality;
}
