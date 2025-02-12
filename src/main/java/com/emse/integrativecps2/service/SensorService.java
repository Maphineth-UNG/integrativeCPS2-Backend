//package com.emse.integrativecps2.service;
//
//import com.emse.integrativecps2.entity.SensorData;
//import com.emse.integrativecps2.repository.SensorDataRepository;
//import org.eclipse.californium.core.CoapClient;
//import org.eclipse.californium.core.CoapResponse;
//import org.eclipse.californium.elements.exception.ConnectorException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import java.io.IOException;
//
//@Service
//public class SensorService {
//
//    @Autowired
//    private SensorDataRepository sensorDataRepository;
//
//    @Autowired
//    private RuleService ruleService;
//
//    private static final String COAP_ENDPOINT = "coap://192.168.101.163/sensor";
//    private final CoapClient coapClient = new CoapClient(COAP_ENDPOINT);
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    /**
//     * Fetches sensor data from the CoAP device and stores it in the database.
//     */
//    @Scheduled(fixedRate = 10000) // Fetch data every 10 seconds
//    public void fetchAndProcessSensorData() {
//        System.out.println("📡 Fetching sensor data from CoAP endpoint...");
//        try {
//            CoapResponse response = coapClient.get();
//            if (response != null && response.isSuccess()) {
//                String jsonResponse = response.getResponseText();
//                SensorData sensorData = parseSensorData(jsonResponse);
//                saveSensorData(sensorData);
//                System.out.println("✅ Sensor data received and saved: " + jsonResponse);
//            } else {
//                System.err.println("⚠️ Warning: Failed to retrieve sensor data from CoAP server. Response is null or unsuccessful.");
//            }
//        } catch (ConnectorException e) {
//            System.err.println("❌ CoAP ConnectorException: Could not connect to " + COAP_ENDPOINT);
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.err.println("❌ IOException: Error processing sensor data from CoAP.");
//            e.printStackTrace();
//        } catch (Exception e) {
//            System.err.println("❌ Unexpected Error: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Parses the CoAP JSON response into a SensorData object.
//     */
//    private SensorData parseSensorData(String jsonResponse) throws IOException {
//        JsonNode root = objectMapper.readTree(jsonResponse);
//        SensorData sensorData = new SensorData();
//        sensorData.setTemperature(root.get("temperature").asDouble());
//        sensorData.setHumidity(root.get("humidity").asDouble());
//        sensorData.setAirQuality(root.get("airQuality").asDouble());
//        return sensorData;
//    }
//
//    public SensorData saveSensorData(SensorData sensorData) {
//        sensorDataRepository.save(sensorData);
//        ruleService.evaluateRules(sensorData);
//        return sensorData;
//    }
//
//    public SensorData getLatestSensorData() {
//        return sensorDataRepository.findAll().stream().reduce((first, second) -> second).orElse(null);
//    }
//}

package com.emse.integrativecps2.service;

import com.emse.integrativecps2.entity.SensorData;
import com.emse.integrativecps2.repository.SensorDataRepository;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.elements.exception.ConnectorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@Service
public class SensorService {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Autowired
    private SensorRuleService sensorRuleService;

    private static final String COAP_ENDPOINT = "coap://192.168.101.163/sensor";
    private final CoapClient coapClient = new CoapClient(COAP_ENDPOINT);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedRate = 10000) // Fetch sensor data every 10s
    public void fetchAndProcessSensorData() {
        System.out.println("📡 Fetching sensor data from CoAP endpoint...");
        try {
            CoapResponse response = coapClient.get();
            if (response != null && response.isSuccess()) {
                SensorData sensorData = parseSensorData(response.getResponseText());
                saveSensorData(sensorData);
            }
        } catch (Exception e) {
            System.err.println("❌ Error retrieving sensor data: " + e.getMessage());
        }
    }

    private SensorData parseSensorData(String jsonResponse) throws IOException {
        JsonNode root = objectMapper.readTree(jsonResponse);
        SensorData sensorData = new SensorData();
        sensorData.setTemperature(root.get("temperature").asDouble());
        sensorData.setHumidity(root.get("humidity").asDouble());
        sensorData.setAirQuality(root.get("airQuality").asDouble());
        return sensorData;
    }

    public SensorData saveSensorData(SensorData sensorData) {
        sensorDataRepository.save(sensorData);
        sensorRuleService.evaluateSensorData(sensorData);
        return sensorData;
    }

    // Add getLatestSensorData() method
    public SensorData getLatestSensorData() {
        return sensorDataRepository.findAll()
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }
}
