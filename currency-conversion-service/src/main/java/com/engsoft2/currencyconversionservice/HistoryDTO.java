package com.engsoft2.currencyconversionservice;

public class HistoryDTO {

    private String from;
    private String to;

    public HistoryDTO() {
    }

    public HistoryDTO(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "HistoryDTO[from=" + from + ", to=" + to + "]";
    }
}