package com.securin.weather.service;

import com.opencsv.CSVReader;
import com.securin.weather.model.Weather;
import com.securin.weather.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    private final WeatherRepository repository;

    public CsvService(WeatherRepository repository) {
        this.repository = repository;
    }

    public void loadCsv(String path) throws Exception {

        CSVReader reader = new CSVReader(new FileReader(path));
        String[] line;

       
        reader.readNext();

        
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");

        List<Weather> weatherList = new ArrayList<>();

        while ((line = reader.readNext()) != null) {

            try {

                Weather w = new Weather();

                
                String dateValue = safeString(line[0]);
                if (dateValue != null) {
                    w.setDatetimeUtc(LocalDateTime.parse(dateValue, formatter));
                }

                w.setConds(safeString(line[1]));
                w.setDewptm(parseDouble(line[2]));
                w.setFog(parseInt(line[3]));
                w.setHail(parseInt(line[4]));
                w.setHeatindexm(parseDouble(line[5]));
                w.setHum(parseDouble(line[6]));
                w.setPrecipm(parseDouble(line[7]));
                w.setPressurem(parseDouble(line[8]));
                w.setRain(parseInt(line[9]));
                w.setSnow(parseInt(line[10]));
                w.setTempm(parseDouble(line[11]));
                w.setThunder(parseInt(line[12]));
                w.setTornado(parseInt(line[13]));
                w.setVism(parseDouble(line[14]));
                w.setWdird(parseDouble(line[15]));
                w.setWdire(safeString(line[16]));
                w.setWgustm(parseDouble(line[17]));
                w.setWindchillm(parseDouble(line[18]));
                w.setWspdm(parseDouble(line[19]));

                weatherList.add(w);

            } catch (Exception e) {
                
                System.out.println("Skipping invalid row...");
            }
        }

        reader.close();

        
        repository.saveAll(weatherList);

        System.out.println("CSV Upload Completed Successfully!");
    }

    private Double parseDouble(String value) {
        if (value == null) return null;

        value = value.trim();

        if (value.isEmpty() ||
                value.equalsIgnoreCase("N/A") ||
                value.equalsIgnoreCase("NA") ||
                value.equalsIgnoreCase("null") ||
                value.equals("-")) {
            return null;
        }

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer parseInt(String value) {
        if (value == null) return null;

        value = value.trim();

        if (value.isEmpty() ||
                value.equalsIgnoreCase("N/A") ||
                value.equalsIgnoreCase("NA") ||
                value.equalsIgnoreCase("null") ||
                value.equals("-")) {
            return null;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String safeString(String value) {
        if (value == null) return null;

        value = value.trim();

        if (value.isEmpty() ||
                value.equalsIgnoreCase("N/A") ||
                value.equalsIgnoreCase("NA") ||
                value.equalsIgnoreCase("null")) {
            return null;
        }

        return value;
    }
}