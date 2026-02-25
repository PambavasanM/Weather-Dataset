package com.securin.weather.repository;

import com.securin.weather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    
    List<Weather> findByDatetimeUtcBetween(LocalDateTime start,
                                           LocalDateTime end);

    
    @Query("SELECT w FROM Weather w WHERE MONTH(w.datetimeUtc) = :month")
    List<Weather> findByMonth(@Param("month") int month);

   
    @Query("SELECT w FROM Weather w WHERE YEAR(w.datetimeUtc) = :year AND MONTH(w.datetimeUtc) = :month")
    List<Weather> findByYearAndMonth(@Param("year") int year,
                                     @Param("month") int month);
}