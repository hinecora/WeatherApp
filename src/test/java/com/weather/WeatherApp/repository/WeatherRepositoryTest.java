package com.weather.WeatherApp.repository;

import com.weather.WeatherApp.entity.Weather;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class WeatherRepositoryTest {

    @Autowired
    WeatherRepository weatherRepository;

    @Test
    void findByDate() {
        LocalDate date = LocalDate.now();
        Weather weather = weatherRepository.findByDate(date);
        Weather weather1 = null;
        Weather weather2 = new Weather();

        assertThat(weather.getDate()).isEqualTo(date);
        assertThat(weather1 == null);
        assertThat(weather2.getDate()).isNotEqualTo(date);
    }
}