package com.emse.integrativecps2.controller;

import com.emse.integrativecps2.entity.WeatherRule;
import com.emse.integrativecps2.service.WeatherRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather/rules")
public class WeatherRuleController {

    @Autowired
    private WeatherRuleService weatherRuleService;

    @PostMapping
    public String addWeatherRule(@RequestBody WeatherRule rule) {
        weatherRuleService.addWeatherRule(rule);
        return "Weather rule saved successfully!";
    }

    @GetMapping
    public List<WeatherRule> getAllWeatherRules() {
        return weatherRuleService.getAllWeatherRules();
    }
}
