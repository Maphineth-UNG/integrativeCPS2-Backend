package com.emse.integrativecps2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@Service
public class WeatherService {

    private final String API_KEY = "a1b64b3b5cf5cf7a8ec89534a511dd37"; // Replace with your actual OpenWeather API key
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String getCurrentWeather(String location) throws IOException {
        // Split latitude and longitude from the location string (assuming "lat,lon" format)
        String[] latLon = location.split(",");
        if (latLon.length != 2) {
            throw new IllegalArgumentException("Location must be in 'lat,lon' format");
        }

        String lat = latLon[0].trim();
        String lon = latLon[1].trim();

        // Construct correct OpenWeather API URL
        String url = UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/weather")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", API_KEY)  // Use 'appid' instead of 'apiKey'
                .queryParam("units", "metric") // Returns temperature in Celsius
                .toUriString();

        // Debugging: Print URL
        System.out.println("Fetching weather data from: " + url);

        // Make API request
        String response = restTemplate.getForObject(url, String.class);

        // Parse JSON response
        JsonNode root = objectMapper.readTree(response);

        return root.toString(); // Return JSON response
    }
}
