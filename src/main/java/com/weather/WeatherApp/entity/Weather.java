package com.weather.WeatherApp.entity;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "weather_history")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "weather_date")
    private LocalDate date;

    @Column(name = "weather_value")
    private String value;

}
