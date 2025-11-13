package com.engsoft2.historyservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryController {

    private final HistoryRepository repository;

    public HistoryController(HistoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/history")
    public List<History> getAll() {
        return repository.findAll();
    }
}