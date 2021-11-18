package co.vison.demo.service;

import co.vison.demo.exception.ElementNotFoundException;
import co.vison.demo.model.City;
import co.vison.demo.model.Weather;
import co.vison.demo.model.WeatherType;
import co.vison.demo.repository.CityRepository;
import co.vison.demo.repository.WeatherRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private CityRepository cityRepository;

    @Autowired
    @InjectMocks
    private WeatherService weatherService;

    City mumbai;
    City banglore;
    Weather mumbaiWeather;
    Weather bangloreWeather;

    @BeforeEach
    public void setUp() {
        mumbai = City.builder().name("Mumbai").build();
        banglore = City.builder().name("Banglore").build();

        mumbaiWeather = Weather.builder()
                .city(mumbai)
                .weatherType(WeatherType.CLOUDY)
                .date(LocalDate.of(2021, 11, 16))
                .build();

        bangloreWeather = Weather.builder()
                .city(mumbai)
                .weatherType(WeatherType.OVERCAST)
                .date(LocalDate.of(2021, 11, 18))
                .build();
    }

    @Test
    public void testMockCreation() {
        assertNotNull(weatherService);
        assertNotNull(weatherRepository);
        assertNotNull(cityRepository);
        assertNotNull(mumbai);
        assertNotNull(mumbaiWeather);
        assertNotNull(banglore);
        assertNotNull(bangloreWeather);
    }

    @Test
    void getAllWeatherData() {

        when(weatherRepository.findAll())
                .thenReturn(List.of(mumbaiWeather, bangloreWeather));
        List<Weather> weather = weatherService.getAllWeatherData();
        verify(weatherRepository).findAll();

        assertEquals(List.of(mumbaiWeather, bangloreWeather), weather);
    }

    @Test
    void getWeatherDataByCity() {

        when(weatherRepository.findTodaysWeatherByCity(any()))
                .thenReturn(java.util.Optional.ofNullable(mumbaiWeather));

        when(cityRepository.findByName(any()))
                .thenReturn(java.util.Optional.ofNullable(mumbai));

        Weather weather = weatherService.getWeatherDataByCity("Mumbai");

        verify(weatherRepository).findTodaysWeatherByCity(any());
        assertNotNull(weather);
        assertEquals(mumbaiWeather.getWeatherType(), weather.getWeatherType());

    }

    @Test
    void getWeatherDataByCityThrowsException() {

        assertThrows(ElementNotFoundException.class, ()->weatherService.getWeatherDataByCity("Banglore"));
    }


    @Test
    void getWeatherForecastDataByCity() {


        when(weatherRepository.findForecastedWeatherByCity(any(),any(),any()))
                .thenReturn(Optional.of(List.of(mumbaiWeather,bangloreWeather)));

        when(cityRepository.findByName(any()))
                .thenReturn(java.util.Optional.ofNullable(mumbai));

        List<Weather> weather = weatherService.getWeatherForecastDataByCity("Banglore");

        verify(weatherRepository).findForecastedWeatherByCity(any(),any(),any());
        assertNotNull(weather);
        assertEquals(List.of(mumbaiWeather,bangloreWeather).get(0).getWeatherType(), weather.get(0).getWeatherType());



    }

    @AfterEach
    void tearDown() {

        mumbai = null;
        banglore = null;
        mumbaiWeather = null;
        bangloreWeather = null;

    }
}