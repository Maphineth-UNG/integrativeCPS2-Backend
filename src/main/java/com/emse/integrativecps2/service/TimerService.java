package com.emse.integrativecps2.service;

import com.emse.integrativecps2.entity.SensorData;
import com.emse.integrativecps2.model.SensorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TimerService {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private SensorService sensorService;

    /**
     * 🔹 Runs every 10 seconds (Fixed Rate)
     * This method fetches sensor data and evaluates rules at a fixed interval.
     */
    @Scheduled(fixedRate = 10000) // Executes every 10 seconds
    public void executeRulesAtFixedRate() {
        System.out.println("⏳ Fixed Rate Execution - Evaluating Rules...");
        fetchAndEvaluateRules();
    }

    /**
     * 🔹 Runs every minute at second 0 (Cron Job)
     * This method runs at specific times using cron expressions.
     */
    @Scheduled(cron = "0 * * * * *") // Runs every minute
    public void executeRulesWithCronJob() {
        System.out.println("⏳ Cron Job Execution - Evaluating Rules...");
        fetchAndEvaluateRules();
    }

    /**
     * Fetches latest sensor data and evaluates rules.
     */
//    private void fetchAndEvaluateRules() {
//        SensorDto latestData = sensorService.getSensorDataFromCoap();
//        if (latestData != null) {
//            ruleService.evaluateRules(latestData);
//        } else {
//            System.out.println("⚠️ No sensor data available for rule evaluation.");
//        }
//    }
        private void fetchAndEvaluateRules() {
            SensorData latestData = sensorService.getLatestSensorData();
            if (latestData != null) {
                ruleService.evaluateRules(latestData);
            } else {
                System.out.println("⚠️ No sensor data available for rule evaluation.");
            }
        }
}
