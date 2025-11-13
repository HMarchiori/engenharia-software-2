package com.engsoft2.historyservice;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromCurrency;
    private String toCurrency;

    private LocalDateTime createdAt;

    public History() {
    }

    public History(String fromCurrency, String toCurrency, LocalDateTime createdAt) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}