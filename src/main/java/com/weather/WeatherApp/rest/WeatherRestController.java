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

    private LocalDate date = LocalDate.now();
    private Document doc;

    {
        try {
            doc = Jsoup.connect("http://yandex.ru/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String temp = doc.getElementsByClass("weather__temp").text();
    private String desc = doc.getElementsByClass("weather__icon").first().attr("title");


    @GetMapping("")
    public String weather() throws IOException {

        if (weatherRepository.findByDate(date) == null) {

            String weather = create();
            return "Сегодня : " + weather + ", " + desc;

        }

        if (weatherRepository.findByDate(date).getDate().equals(date)) {

            if (!weatherRepository.findByDate(date).getValue().equals(temp)) {

                weatherRepository.findByDate(date).setValue(temp);
                return "Сегодня : " + weatherRepository.findByDate(date).getValue() + ", " + desc;

            }

            return "Сегодня : " + weatherRepository.findByDate(date).getValue() + ", " + desc;

        } else {

            String weather = create();
            return "Сегодня : " + weather + ", " + desc;

        }

    }

    public String create() throws IOException {
        Weather weather = new Weather();
        weather.setValue(temp);
        weather.setDate(date);
        weatherRepository.save(weather);

        return weather.getValue();
    }





}
