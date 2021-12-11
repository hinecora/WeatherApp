package com.weather.WeatherApp.repository;

import com.weather.WeatherApp.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Weather findByDate(LocalDate date);

    Weather findByDateNotNull();

}
