package co.vison.demo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherDto {

    Long id;
    String city;
    Integer temperature;
    String weatherType;
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate date;

}
