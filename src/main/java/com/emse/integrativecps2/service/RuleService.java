////package com.emse.integrativecps2.service;
////
////
////import com.emse.integrativecps2.model.SensorDto;
////import org.springframework.stereotype.Service;
////import java.util.HashMap;
////import java.util.Map;
////
////@Service
////public class RuleService {
////
////    public Map<String, String> evaluateSensorRules(SensorDto data) {
////        Map<String, String> actions = new HashMap<>();
////
////        if (data.getTemperature() > 30) {
////            actions.put("Cooling System", "Turn ON");
////        } else if (data.getTemperature() < 5) {
////            actions.put("Heating System", "Turn ON");
////        }
////
////        if (data.getHumidity() > 70) {
////            actions.put("Dehumidifier", "Activate");
////        }
////
////        if (data.getAirQuality() < 50) {
////            actions.put("Air Purifier", "Turn ON");
////        }
////
////        return actions;
////    }
////}
//
//package com.emse.integrativecps2.service;
//
//import com.emse.integrativecps2.model.RuleDto;
//import com.emse.integrativecps2.model.SensorDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.scheduling.annotation.Scheduled;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class RuleService {
//    private final List<RuleDto> rules = new ArrayList<>();
//
//    private SensorService sensorService; // Inject SensorService
//
//    private NotificationService notificationService;
//
//    public void addRule(RuleDto rule) {
//        rules.add(rule);
//    }
//
//    public void removeRule(String ruleId) {
//        rules.removeIf(rule -> rule.getId().equals(ruleId));
//    }
//
//    public List<RuleDto> getRules() {
//        return rules;
//    }
//
//    // Evaluates all rules based on sensor data
//    public void evaluateRules(SensorDto sensorData) {
//        for (RuleDto rule : rules) {
//            if (isConditionMet(rule, sensorData)) {
//                executeAction(rule.getAction());
//            }
//
//            // Send email notification
//            String message = "🚀 Rule Triggered: " + rule.getTriggerType() + " " +
//                    rule.getCondition() + " " + rule.getThreshold() +
//                    "\n📌 Action: " + rule.getAction();
//
//            notificationService.sendEmail("user@example.com", "Rule Triggered Alert", message);
//        }
//    }
//
//    private boolean isConditionMet(RuleDto rule, SensorDto sensorData) {
//        double value;
//        switch (rule.getTriggerType()) {
//            case "temperature":
//                value = sensorData.getTemperature();
//                break;
//            case "humidity":
//                value = sensorData.getHumidity();
//                break;
//            default:
//                return false;
//        }
//
//        return switch (rule.getCondition()) {
//            case ">" -> value > rule.getThreshold();
//            case "<" -> value < rule.getThreshold();
//            case "==" -> value == rule.getThreshold();
//            default -> false;
//        };
//    }
//
//    private void executeAction(String action) {
//        System.out.println("Executing Action: " + action);
//        // Add real action execution logic (e.g., control a device, send a notification)
//
//    }
//
//    // Scheduled task to trigger rule evaluation every 10 seconds
//    @Scheduled(fixedRate = 10000)
//    public void scheduledRuleEvaluation() {
//        System.out.println("Scheduled Rule Evaluation Running...");
//        // Here you should fetch the latest sensor data from SensorService
//
//        SensorDto latestData = sensorService.getSensorDataFromCoap(); // Fetch latest data
//        if (latestData != null) {
//            evaluateRules(latestData); // Evaluate rules
//        } else {
//            System.out.println("⚠️ No sensor data available for rule evaluation.");
//        }
//    }
//}
//

package com.emse.integrativecps2.service;

import com.emse.integrativecps2.entity.Rule;
import com.emse.integrativecps2.entity.SensorData;
import com.emse.integrativecps2.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    public Rule addRule(Rule rule) {
        return ruleRepository.save(rule);
    }

    public void removeRule(Long ruleId) {
        ruleRepository.deleteById(ruleId);
    }

    public List<Rule> getRules() {
        return ruleRepository.findAll();
    }

    public void evaluateRules(SensorData sensorData) {
        List<Rule> rules = ruleRepository.findAll();
        for (Rule rule : rules) {
            if (isConditionMet(rule, sensorData)) {
                executeAction(rule.getAction());
            }
        }
    }

    private boolean isConditionMet(Rule rule, SensorData sensorData) {
        double value = switch (rule.getTriggerType()) {
            case "temperature" -> sensorData.getTemperature();
            case "humidity" -> sensorData.getHumidity();
            default -> -1;
        };

        return switch (rule.getCondition()) {
            case ">" -> value > rule.getThreshold();
            case "<" -> value < rule.getThreshold();
            case "==" -> value == rule.getThreshold();
            default -> false;
        };
    }

    private void executeAction(String action) {
        System.out.println("Executing Action: " + action);
    }
}

