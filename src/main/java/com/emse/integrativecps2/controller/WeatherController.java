//package com.emse.integrativecps2.controller;
//
//import com.emse.integrativecps2.service.WeatherService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import java.io.IOException;
//
//@RestController
//public class WeatherController {
//
//    @Autowired
//    private WeatherService weatherService;
//
//    @GetMapping("/weather/current")
//    public String getCurrentWeather(@RequestParam String location) throws IOException {
//        return weatherService.getCurrentWeather(location);
//    }
//}

package com.emse.integrativecps2.controller;
import com.emse.integrativecps2.service.WeatherService;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.io.IOException;

//@RestController
//@RequestMapping("/weather")
//public class WeatherController {
//
//    private final WeatherService weatherService;
//    private final SimpMessagingTemplate messagingTemplate;
//
//    public WeatherController(WeatherService weatherService, SimpMessagingTemplate messagingTemplate) {
//        this.weatherService = weatherService;
//        this.messagingTemplate = messagingTemplate;
//    }
//
//    @GetMapping("/current")
//    public String getCurrentWeather(@RequestParam String location) throws IOException {
//        return weatherService.getCurrentWeather(location);
//    }
//
//    @Scheduled(fixedRate = 10000) // Send updates every 10 seconds
//    public void sendWeatherUpdates() throws IOException {
//        String location = "45.764,4.835"; // Example: Lyon's coordinates
//        String weatherData = weatherService.getCurrentWeather(location);
//        messagingTemplate.convertAndSend("/topic/weather", weatherData);
//    }
//}

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private String currentLocation = "45.758,4.832"; // Default: SE

    @GetMapping("/current")
    public String getCurrentWeather(@RequestParam String location) throws IOException {
        currentLocation = location; // Store new location
        return weatherService.getCurrentWeather(location);
    }

    @Scheduled(fixedRate = 10000) // Update every 10s
    public void sendWeatherUpdates() throws IOException {
        String weatherData = weatherService.getCurrentWeather(currentLocation);
        messagingTemplate.convertAndSend("/topic/weather", weatherData);
    }
}

