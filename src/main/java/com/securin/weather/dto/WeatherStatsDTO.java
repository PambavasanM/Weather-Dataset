package com.securin.weather.dto;

public class WeatherStatsDTO {

    private int month;
    private Double max;
    private Double min;
    private Double median;

    public WeatherStatsDTO(int month, Double max,
                           Double min, Double median) {
        this.month = month;
        this.max = max;
        this.min = min;
        this.median = median;
    }

    public int getMonth() {
        return month;
    }

    public Double getMax() {
        return max;
    }

    public Double getMin() {
        return min;
    }

    public Double getMedian() {
        return median;
    }
}