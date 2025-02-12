package com.emse.integrativecps2.service;

import com.emse.integrativecps2.entity.WeatherRule;
import com.emse.integrativecps2.entity.WeatherData;
import com.emse.integrativecps2.repository.WeatherRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherRuleService {

    @Autowired
    private WeatherRuleRepository weatherRuleRepository;

    @Autowired
    private NotificationService notificationService;

    public WeatherRule addWeatherRule(WeatherRule rule) {
        return weatherRuleRepository.save(rule);
    }

    public List<WeatherRule> getAllWeatherRules() {
        return weatherRuleRepository.findAll();
    }

    public void evaluateWeatherConditions(WeatherData weatherData) {
        List<WeatherRule> rules = weatherRuleRepository.findAll();
        for (WeatherRule rule : rules) {
            if (isConditionMet(rule, weatherData)) {
                sendAlert(rule.getEmail(), rule.getTriggerType(), weatherData);
            }
        }
    }

    private boolean isConditionMet(WeatherRule rule, WeatherData weatherData) {
        double value = switch (rule.getTriggerType()) {
            case "temperature" -> weatherData.getTemperature();
            case "humidity" -> weatherData.getHumidity();
            default -> -1;
        };

        return switch (rule.getCondition()) {
            case ">" -> value > rule.getThreshold();
            case "<" -> value < rule.getThreshold();
            case "==" -> value == rule.getThreshold();
            default -> false;
        };
    }

    private void sendAlert(String email, String triggerType, WeatherData weatherData) {
        String subject = "Weather Alert: " + triggerType + " Condition Met";
        String message = "The " + triggerType + " has reached the set threshold.\n\n" +
                "Current Value: " + (triggerType.equals("temperature") ? weatherData.getTemperature() : weatherData.getHumidity()) + "\n" +
                "Location: " + weatherData.getLocation();
        notificationService.sendEmail(email, subject, message);
        System.out.println("📩 Email sent to " + email);
    }
}
