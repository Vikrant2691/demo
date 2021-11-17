package co.vison.demo.service;

import co.vison.demo.exception.ElementNotFoundException;
import co.vison.demo.model.City;
import co.vison.demo.model.Weather;
import co.vison.demo.repository.CityRepository;
import co.vison.demo.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final CityRepository cityRepository;

    public WeatherService(WeatherRepository weatherRepository, CityRepository cityRepository) {
        this.weatherRepository = weatherRepository;
        this.cityRepository = cityRepository;
    }

    public List<Weather> getAllWeatherData() {
        return weatherRepository.findAll();
    }

    public Weather getWeatherDataByCity(String city) throws ElementNotFoundException {


        Optional<City> savedCity = cityRepository.findByName(city);

        if(savedCity.isEmpty()){
            throw new ElementNotFoundException("City "+city+" not found");
        }

        Optional<Weather> weather=weatherRepository.findTodaysWeatherByCity(savedCity.get());

        if(weather.isEmpty()){
            throw new ElementNotFoundException("Weather information for "+city+" not found");
        }

        return weather.get();
    }


    public List<Weather> getWeatherForecastDataByCity(String city) throws ElementNotFoundException {

        Optional<City> savedCity = cityRepository.findByName(city);

        if(savedCity.isEmpty()){
            throw new ElementNotFoundException("City "+city+" not found");
        }

        List<Weather> weatherList = weatherRepository.findForecastedWeatherByCity(savedCity.get(), LocalDate.now(),LocalDate.now().plusDays(3)).get();

        if(weatherList.isEmpty()){
            throw new ElementNotFoundException("Weather information for "+city+" not found");
        }


        return weatherList;
    }


}
