package co.vison.demo.controller;

import co.vison.demo.dto.WeatherDto;
import co.vison.demo.mapper.WeatherMapper;
import co.vison.demo.model.City;
import co.vison.demo.model.Weather;
import co.vison.demo.model.WeatherType;
import co.vison.demo.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;
    @Mock
    private WeatherMapper weatherMapper;

//    @Autowired
    @InjectMocks
    private WeatherController weatherController;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    City mumbai;
    City banglore;
    Weather mumbaiWeather;
    WeatherDto mumbaiWeatherDto;
    Weather bangloreWeather;

    @BeforeEach
    void setUp() {

        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        weatherController= new WeatherController(weatherService,weatherMapper);

        mumbai = City.builder().name("Mumbai").build();
        banglore = City.builder().name("Banglore").build();

        mumbaiWeather = Weather.builder()
                .city(mumbai)
                .weatherType(WeatherType.CLOUDY)
                .date(LocalDate.of(2021, 11, 16))
                .build();

        mumbaiWeatherDto = WeatherDto.builder()
                .city("Mumbai")
                .weatherType(WeatherType.CLOUDY.toString())
                .date(LocalDate.of(2021, 11, 16))
                .build();


        bangloreWeather = Weather.builder()
                .city(mumbai)
                .weatherType(WeatherType.OVERCAST)
                .date(LocalDate.of(2021, 11, 18))
                .build();


        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();

    }

    @AfterEach
    void tearDown() {

        mumbai = null;
        banglore = null;
        mumbaiWeather = null;
        bangloreWeather = null;

    }

    @Test
    public void testMockCreation() {
        assertNotNull(weatherService);
        assertNotNull(mumbai);
        assertNotNull(mumbaiWeather);
        assertNotNull(banglore);
        assertNotNull(bangloreWeather);
        assertNotNull(objectMapper);
    }

    @Test
    void getWeatherDataByCity() throws Exception {

        when(weatherService.getWeatherDataByCity(anyString())).thenReturn(mumbaiWeather);

        when(weatherMapper.weatherToWeatherDto(any())).thenReturn(mumbaiWeatherDto);

        weatherController.getWeatherDataByCity("Mumbai");

        assertNotNull(mumbaiWeather);

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/weather?city=Mumbai")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mumbaiWeather)))
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andDo(MockMvcResultHandlers.print());

    }
}