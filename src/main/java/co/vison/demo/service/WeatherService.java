package co.vison.demo.service;

import co.vison.demo.model.City;
import co.vison.demo.model.Weather;
import co.vison.demo.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WeatherService{

    WeatherRepository weatherRepository;

    public Weather saveWeather(Weather weather){
        return weatherRepository.save(weather);
    }

    public Weather getWeather(City city) throws NoSuchElementException {
        return weatherRepository.findWeatherByCity(city).get();
    }

}
