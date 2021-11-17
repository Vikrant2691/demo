package co.vison.demo.controller;


import co.vison.demo.dto.WeatherDto;
import co.vison.demo.exception.ElementNotFoundException;
import co.vison.demo.mapper.WeatherMapper;
import co.vison.demo.repository.WeatherRepository;
import co.vison.demo.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherMapper weatherMapper;

    public WeatherController(WeatherService weatherService, WeatherMapper weatherMapper) {
        this.weatherService = weatherService;
        this.weatherMapper = weatherMapper;
    }


    @GetMapping("/weather/all")
    public ResponseEntity<List<WeatherDto>> getAllWeatherData() {

        try {
            return ResponseEntity.ok(weatherService.getAllWeatherData().stream().map(
                    weatherMapper::weatherToWeatherDto
            ).collect(Collectors.toUnmodifiableList()));
        }
        catch (NoSuchElementException noSuchElementException){
            return ResponseEntity.noContent().build();

        }

    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherDto> getWeatherDataByCity(@PathParam("city") String city) {
           return ResponseEntity.ok(weatherMapper.weatherToWeatherDto(weatherService.getWeatherDataByCity(city)));
    }

    @GetMapping("/weather/forecast")
    public ResponseEntity<List<WeatherDto>> getWeatherForecastDataByCity(@PathParam("city") String city) {

            return ResponseEntity.ok(weatherService.getWeatherForecastDataByCity(city).stream().map(
                    weatherMapper::weatherToWeatherDto
            ).collect(Collectors.toUnmodifiableList()));

    }


}
