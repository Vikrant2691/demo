package co.vison.demo.repository;

import co.vison.demo.model.City;
import co.vison.demo.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

    @Query("SELECT w from Weather w where w.city= :city and w.date = CURRENT_DATE")
    public Optional<Weather> findTodaysWeatherByCity(@Param("city") City city);

    @Query("SELECT w from Weather w where w.city= :city and w.date between :startDate and :endDate")
    public Optional<List<Weather>> findForecastedWeatherByCity(@Param("city") City city,
                                                               @Param("startDate") LocalDate startDate,
                                                               @Param("endDate") LocalDate endDate);



}
