package co.vison.demo.repository;

import co.vison.demo.model.City;
import co.vison.demo.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
