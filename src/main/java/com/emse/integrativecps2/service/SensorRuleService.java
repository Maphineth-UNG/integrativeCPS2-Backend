package com.emse.integrativecps2.service;

import com.emse.integrativecps2.entity.SensorRule;
import com.emse.integrativecps2.entity.SensorData;
import com.emse.integrativecps2.repository.SensorRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorRuleService {

    @Autowired
    private SensorRuleRepository sensorRuleRepository;

    @Autowired
    private NotificationService notificationService;

    public SensorRule addSensorRule(SensorRule rule) {
        return sensorRuleRepository.save(rule);
    }

    public List<SensorRule> getAllSensorRules() {
        return sensorRuleRepository.findAll();
    }

    public void evaluateSensorData(SensorData sensorData) {
        List<SensorRule> rules = sensorRuleRepository.findAll();
        for (SensorRule rule : rules) {
            if (isConditionMet(rule, sensorData)) {
                sendAlert(rule.getEmail(), rule.getTriggerType(), sensorData);
            }
        }
    }

    private boolean isConditionMet(SensorRule rule, SensorData sensorData) {
        double value = switch (rule.getTriggerType()) {
            case "temperature" -> sensorData.getTemperature();
            case "humidity" -> sensorData.getHumidity();
            case "airQuality" -> sensorData.getAirQuality();
            default -> -1;
        };

        return switch (rule.getCondition()) {
            case ">" -> value > rule.getThreshold();
            case "<" -> value < rule.getThreshold();
            case "==" -> value == rule.getThreshold();
            default -> false;
        };
    }

    private void sendAlert(String email, String triggerType, SensorData sensorData) {
        String subject = "Sensor Alert: " + triggerType + " Condition Met";
        String message = "The " + triggerType + " has reached the set threshold.\n\n" +
                "Current Value: " + switch (triggerType) {
            case "temperature" -> sensorData.getTemperature();
            case "humidity" -> sensorData.getHumidity();
            case "airQuality" -> sensorData.getAirQuality();
            default -> "Unknown";
        } +
                "\nLocation: Sensor Device";

        notificationService.sendEmail(email, subject, message);
        System.out.println("📩 Email sent to " + email);
    }
}
