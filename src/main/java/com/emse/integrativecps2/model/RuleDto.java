package com.emse.integrativecps2.model;
import lombok.Data;

@Data

public class RuleDto {
    private String id;
    private String triggerType; // e.g., "temperature", "humidity"
    private String condition; // e.g., ">", "<", "=="
    private double threshold; // Value for comparison
    private String action; // e.g., "TURN_ON_FAN", "SEND_ALERT"
}




