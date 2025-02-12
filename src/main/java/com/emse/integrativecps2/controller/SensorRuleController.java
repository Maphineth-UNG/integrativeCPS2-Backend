package com.emse.integrativecps2.controller;

import com.emse.integrativecps2.entity.SensorRule;
import com.emse.integrativecps2.service.SensorRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensor/rules")
public class SensorRuleController {

    @Autowired
    private SensorRuleService sensorRuleService;

    @PostMapping
    public String addSensorRule(@RequestBody SensorRule rule) {
        sensorRuleService.addSensorRule(rule);
        return "Sensor rule saved successfully!";
    }

    @GetMapping
    public List<SensorRule> getAllSensorRules() {
        return sensorRuleService.getAllSensorRules();
    }
}
