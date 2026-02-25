package com.securin.weather.controller;

import com.securin.weather.dto.WeatherStatsDTO;
import com.securin.weather.model.Weather;
import com.securin.weather.service.CsvService;
import com.securin.weather.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final CsvService csvService;
    private final WeatherService weatherService;

    public WeatherController(CsvService csvService,
                             WeatherService weatherService) {
        this.csvService = csvService;
        this.weatherService = weatherService;
    }

    
    @PostMapping("/upload")
    public String upload() throws Exception {
        csvService.loadCsv("C:/Users/pamba/OneDrive/Desktop/Full Stack Java/SpringBoot/Project/Securin Project/Weather data assessment V.1.0/Assessment 2/testset.csv");
        return "CSV Loaded Successfully!";
    }

   
    @GetMapping("/date/{date}")
    public List<Weather> getByDate(@PathVariable String date) {
        return weatherService.getByDate(date);
    }

    
    @GetMapping("/month/{month}")
    public List<Weather> getByMonth(@PathVariable int month) {
        return weatherService.getByMonth(month);
    }

    
    @GetMapping("/stats/{year}")
    public List<WeatherStatsDTO> getStats(@PathVariable int year) {
        return weatherService.getMonthlyStats(year);
    }
}