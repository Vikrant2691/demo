package co.vison.demo.controller;


import co.vison.demo.dto.WeatherDto;
import co.vison.demo.mapper.WeatherMapper;
import co.vison.demo.repository.WeatherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WeatherController {

    private final WeatherRepository weatherRepository;
    private final WeatherMapper weatherMapper;

    public WeatherController(WeatherRepository weatherRepository, WeatherMapper weatherMapper) {
        this.weatherRepository = weatherRepository;
        this.weatherMapper = weatherMapper;
    }


    @GetMapping("/weather/")
    public ResponseEntity<List<WeatherDto>> getAllWeatherData() {

        return ResponseEntity.ok(weatherRepository.findAll().stream().map(
                weatherMapper::weatherToWeatherDto
        ).collect(Collectors.toUnmodifiableList()));

    }


}
