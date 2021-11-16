package co.vison.demo.controller;


import co.vison.demo.model.Weather;
import co.vison.demo.repository.WeatherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {

    private final WeatherRepository weatherRepository;

    public WeatherController(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @GetMapping("/weather/")
    public ResponseEntity<List<Weather>> getAllWeatherData(){

        return ResponseEntity.ok(weatherRepository.findAll());

    }


}
