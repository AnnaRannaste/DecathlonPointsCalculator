package com.decathlonapplication.controllers;

import com.decathlonapplication.services.DecathlonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


@RestController
public class DecathlonController {
    @Autowired
    private DecathlonService decathlonService;

    @GetMapping("/calculatePoints/{event}/{result}")
    public Map<String, Object> calculatePoints(@PathVariable String event, @PathVariable double result) {
        double points = decathlonService.calculatePoints(event, result);
        Map<String, Object> response = new HashMap<>();
        response.put("event", event);
        response.put("result", result);
        response.put("points", points);
        return response;
    }
}
