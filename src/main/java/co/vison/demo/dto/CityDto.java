package co.vison.demo.dto;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {

    Long id;
    String name;
    Double latitude;
    Double longitude;

}
