package co.vison.demo.repository;

import co.vison.demo.model.City;
import co.vison.demo.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findWeatherByCity(City city);
}
