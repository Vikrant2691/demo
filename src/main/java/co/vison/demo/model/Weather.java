package co.vison.demo.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @OneToOne(cascade = CascadeType.MERGE)
    City city;
    Integer temperature;
    @Enumerated(EnumType.STRING)
    WeatherType weatherType;
    LocalDate date;

}
