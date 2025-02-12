////package com.emse.integrativecps2.controller;
////
////import com.emse.integrativecps2.service.RuleService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.RequestParam;
////import org.springframework.web.bind.annotation.RestController;
////import java.io.IOException;
////import java.util.Map;
////
////@RestController
////public class RuleController {
////
////    @Autowired
////    private RuleService ruleService;
////
////    @GetMapping("/weather/rules")
////    public Map<String, String> getWeatherRules(@RequestParam String location) throws IOException {
////        return ruleService.evaluateWeatherRules(location);
////    }
////}
//
//package com.emse.integrativecps2.controller;
//
//import com.emse.integrativecps2.model.RuleDto;
//import com.emse.integrativecps2.service.RuleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/rules")
//public class RuleController {
//
//    @Autowired
//    private RuleService ruleService;
//
//    @PostMapping
//    public String addRule(@RequestBody RuleDto rule) {
//        ruleService.addRule(rule);
//        return "Rule added successfully!";
//    }
//
//    @DeleteMapping("/{id}")
//    public String removeRule(@PathVariable String id) {
//        ruleService.removeRule(id);
//        return "Rule removed successfully!";
//    }
//
//    @GetMapping
//    public List<RuleDto> getRules() {
//        return ruleService.getRules();
//    }
//}
//
package com.emse.integrativecps2.controller;

import com.emse.integrativecps2.entity.Rule;
import com.emse.integrativecps2.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @PostMapping
    public Rule addRule(@RequestBody Rule rule) {
        return ruleService.addRule(rule);
    }

    @DeleteMapping("/{id}")
    public String removeRule(@PathVariable Long id) {
        ruleService.removeRule(id);
        return "Rule removed successfully!";
    }

    @GetMapping
    public List<Rule> getRules() {
        return ruleService.getRules();
    }
}
