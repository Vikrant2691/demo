package co.vison.demo.dto;


import co.vison.demo.model.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {

    Long id;
    String city;
    String weatherType;
    LocalDate date;

}
