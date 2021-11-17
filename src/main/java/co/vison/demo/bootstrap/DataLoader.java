package co.vison.demo.bootstrap;

import co.vison.demo.model.City;
import co.vison.demo.model.Weather;
import co.vison.demo.model.WeatherType;
import co.vison.demo.repository.CityRepository;
import co.vison.demo.repository.WeatherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final WeatherRepository weatherRepository;
    private final CityRepository cityRepository ;

    public DataLoader(WeatherRepository weatherRepository, CityRepository cityRepository) {
        this.weatherRepository = weatherRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        City mumbai= cityRepository.save(City.builder().name("Mumbai").build());
        City banglore= cityRepository.save(City.builder().name("Banglore").build());
        City tamilNadu= cityRepository.save(City.builder().name("TamilNadu").build());

        weatherRepository.save(Weather.builder()
                .city(mumbai)
                .weatherType(WeatherType.CLOUDY)
                .date(LocalDate.of(2021,11,15))
                .build());

        weatherRepository.save(Weather.builder()
                .city(mumbai)
                .weatherType(WeatherType.OVERCAST)
                .date(LocalDate.of(2021,11,16))
                .build());

        weatherRepository.save(Weather.builder()
                .city(mumbai)
                .weatherType(WeatherType.RAINY)
                .date(LocalDate.of(2021,11,17))
                .build());

        weatherRepository.save(Weather.builder()
                .city(mumbai)
                .weatherType(WeatherType.SUNNY)
                .date(LocalDate.of(2021,11,18))
                .build());

        weatherRepository.save(Weather.builder()
                .city(banglore)
                .weatherType(WeatherType.CLOUDY)
                .date(LocalDate.of(2021,11,15))
                .build());

        weatherRepository.save(Weather.builder()
                .city(banglore)
                .weatherType(WeatherType.OVERCAST)
                .date(LocalDate.of(2021,11,16))
                .build());

        weatherRepository.save(Weather.builder()
                .city(banglore)
                .weatherType(WeatherType.RAINY)
                .date(LocalDate.of(2021,11,17))
                .build());

        weatherRepository.save(Weather.builder()
                .city(banglore)
                .weatherType(WeatherType.SUNNY)
                .date(LocalDate.of(2021,11,18))
                .build());


    }
}
