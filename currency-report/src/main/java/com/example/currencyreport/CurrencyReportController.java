package com.example.currencyreport;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyReportController {

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        return response;
    }

    @GetMapping("/quote")
    public Map<String, Object> getQuote(@RequestParam String from, @RequestParam String to) {
        Map<String, Object> response = new HashMap<>();
        response.put("from", from);
        response.put("to", to);
        response.put("price", 5.42);
        response.put("timestamp", "2025-11-15T13:00:00Z");
        return response;
    }
}