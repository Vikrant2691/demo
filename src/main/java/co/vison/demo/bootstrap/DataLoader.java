package co.vison.demo.bootstrap;

import co.vison.demo.model.City;
import co.vison.demo.model.Weather;
import co.vison.demo.model.WeatherType;
import co.vison.demo.repository.CityRepository;
import co.vison.demo.repository.WeatherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final WeatherRepository weatherRepository;
    private final CityRepository cityRepository;

    public DataLoader(WeatherRepository weatherRepository, CityRepository cityRepository) {
        this.weatherRepository = weatherRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        City mumbai = cityRepository.save(City.builder().name("Mumbai").build());
        City banglore = cityRepository.save(City.builder().name("Banglore").build());
        City tamilNadu = cityRepository.save(City.builder().name("TamilNadu").build());

        List<City> cities= List.of(mumbai,banglore,tamilNadu);

        List<WeatherType> weatherSet = List.of(WeatherType.values());

        populateData(cities,weatherSet);

    }

    private void populateData(List<City> cities, List<WeatherType> weatherTypes) {


        for (int i = 0; i < cities.size(); i++) {

            for (int j = 0; j < 10; j++) {
                weatherRepository.save(Weather.builder()
                        .city(cities.get(i))
                        .weatherType(weatherTypes.get((int) (Math.random() * weatherTypes.size())))
                        .temperature((int) (Math.random() * 67))
                        .date(LocalDate.now().plusDays(j))
                        .build());
            }
        }


    }
}
