package co.vison.demo.mapper;

import co.vison.demo.dto.WeatherDto;
import co.vison.demo.model.City;
import co.vison.demo.model.Weather;
import co.vison.demo.model.WeatherType;
import co.vison.demo.repository.CityRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class WeatherMapper {

    private final ModelMapper modelMapper;
    private final CityRepository cityRepository;

    public WeatherMapper(ModelMapper modelMapper, CityRepository cityRepository) {
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
    }

    public WeatherDto weatherToWeatherDto(Weather weather) {
        return modelMapper.typeMap(Weather.class, WeatherDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getCity().getName(), WeatherDto::setCity);
                    mapper.map(src -> src.getWeatherType().toString(), WeatherDto::setWeatherType);
                }).map(weather);


    }

    public Weather weatherDtoToWeather(WeatherDto weatherDto) throws NoSuchElementException, IllegalArgumentException {
        Converter<String, City> toCity =
                ctx -> cityRepository.findByName(weatherDto.getCity()).orElseThrow(NoSuchElementException::new);

        Converter<String, WeatherType> toWeatherType =
                twt -> WeatherType.valueOf(weatherDto.getWeatherType());

        return modelMapper.typeMap(WeatherDto.class, Weather.class)
                .addMappings(mapper -> {
                    mapper.using(toCity).map(WeatherDto::getCity, Weather::setCity);
                    mapper.using(toWeatherType).map(WeatherDto::getWeatherType, Weather::setWeatherType);
                })
                .map(weatherDto);
    }

}
