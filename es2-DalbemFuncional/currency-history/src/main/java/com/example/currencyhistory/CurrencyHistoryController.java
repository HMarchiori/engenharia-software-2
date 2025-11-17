package com.example.currencyhistory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.*;

@RestController
public class CurrencyHistoryController {

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }

    @GetMapping("/history")
    public Map<String, Object> getHistory(@RequestParam String from, @RequestParam String to) {

        List<Map<String, Object>> values = List.of(
                Map.of("timestamp", Instant.now().minusSeconds(600).toString(), "price", 5.42),
                Map.of("timestamp", Instant.now().minusSeconds(300).toString(), "price", 5.47),
                Map.of("timestamp", Instant.now().toString(), "price", 5.51)
        );

        Map<String, Object> response = new HashMap<>();
        response.put("from", from);
        response.put("to", to);
        response.put("values", values);

        return response;
    }
}
