package com.weather.WeatherApp.rest;

import com.weather.WeatherApp.entity.Weather;
import com.weather.WeatherApp.repository.WeatherRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/weather")
public class WeatherRestController {

    @Autowired
    private WeatherRepository weatherRepository;

    @GetMapping("")
    public String weather() throws IOException {

        LocalDate date = LocalDate.now();
        Document doc = Jsoup.connect("http://yandex.ru/").get();
        String temp = doc.getElementsByClass("weather__temp").text();
        String desc = doc.getElementsByClass("weather__icon").first().attr("title");

        if (weatherRepository.findByDate(date) == null) {

            String weather = create(date, temp);

            return "Сегодня : " + weather + ", " + desc;

        }

        if (weatherRepository.findByDate(date).getDate().equals(date)) {

            if (!weatherRepository.findByDate(date).getValue().equals(temp)) {

                weatherRepository.findByDate(date).setValue(temp);

                weatherRepository.save(weatherRepository.findByDate(date));

                return "Сегодня : " + weatherRepository.findByDate(date).getValue() + ", " + desc;

            }

            return "Сегодня : " + weatherRepository.findByDate(date).getValue() + ", " + desc;

        } else {

            String weather = create(date, temp);

            return "Сегодня : " + weather + ", " + desc;

        }

    }

    public String create(LocalDate date, String temp) {
        Weather weather = new Weather();
        weather.setValue(temp);
        weather.setDate(date);
        weatherRepository.save(weather);

        return weather.getValue();
    }





}
