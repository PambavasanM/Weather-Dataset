package com.securin.weather.service;

import com.securin.weather.dto.WeatherStatsDTO;
import com.securin.weather.model.Weather;
import com.securin.weather.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class WeatherService {

    private final WeatherRepository repository;

    public WeatherService(WeatherRepository repository) {
        this.repository = repository;
    }

    // 1️⃣ Get weather details by date (entire day)
    public List<Weather> getByDate(String date) {

        LocalDate localDate = LocalDate.parse(date);

        LocalDateTime start = localDate.atStartOfDay();
        LocalDateTime end = localDate.atTime(23, 59, 59);

        return repository.findByDatetimeUtcBetween(start, end);
    }

    // 2️⃣ Get weather by month (across 20 years)
    public List<Weather> getByMonth(int month) {
        return repository.findByMonth(month);
    }

    // 3️⃣ Get Monthly Min, Max, Median temperature for a given year
    public List<WeatherStatsDTO> getMonthlyStats(int year) {

        List<WeatherStatsDTO> result = new ArrayList<>();

        for (int month = 1; month <= 12; month++) {

            List<Weather> data =
                    repository.findByYearAndMonth(year, month);

            List<Double> temps = new ArrayList<>();

            for (Weather w : data) {
                if (w.getTempm() != null)
                    temps.add(w.getTempm());
            }

            if (temps.isEmpty()) continue;

            Collections.sort(temps);

            Double min = temps.get(0);
            Double max = temps.get(temps.size() - 1);
            Double median;

            int size = temps.size();

            if (size % 2 == 0) {
                median = (temps.get(size/2 - 1)
                        + temps.get(size/2)) / 2.0;
            } else {
                median = temps.get(size/2);
            }

            result.add(new WeatherStatsDTO(month, max, min, median));
        }

        return result;
    }
}