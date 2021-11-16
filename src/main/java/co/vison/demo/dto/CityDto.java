package co.vison.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {

    Long id;
    String name;
    Double latitude;
    Double longitude;

}
