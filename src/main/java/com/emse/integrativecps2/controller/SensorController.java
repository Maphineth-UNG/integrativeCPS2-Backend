////package com.emse.integrativecps2.controller;
////
////
////import com.emse.integrativecps2.model.SensorDto;
////import com.emse.integrativecps2.service.SensorService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.web.bind.annotation.*;
////
////@RestController
////@RequestMapping("/sensor")
////public class SensorController {
////
////    @Autowired
////    private SensorService sensorService;
////
////    @PostMapping("/data")
////    public String receiveSensorData(@RequestBody SensorDto data) {
////        sensorService.processSensorData(data);
////        return "Sensor data received successfully!";
////    }
////
////    @GetMapping("/latest")
////    public SensorDto getLatestSensorData() {
////        return sensorService.getLatestSensorData();
////    }
////}
//
//package com.emse.integrativecps2.controller;
//
//import com.emse.integrativecps2.model.SensorDto;
//import com.emse.integrativecps2.service.SensorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/sensor")
//public class SensorController {
//
//    @Autowired
//    private SensorService sensorService;
//
//    @GetMapping
//    public SensorDto getSensorData() {
//        return sensorService.getSensorDataFromCoap();
//    }
//}
//

package com.emse.integrativecps2.controller;

import com.emse.integrativecps2.entity.SensorData;
import com.emse.integrativecps2.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @PostMapping("/data")
    public SensorData receiveSensorData(@RequestBody SensorData sensor) {
        return sensorService.saveSensorData(sensor);
    }

    @GetMapping("/latest")
    public SensorData getLatestSensorData() {
        return sensorService.getLatestSensorData();
    }
}

