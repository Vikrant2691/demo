package co.vison.demo.model;


import lombok.*;
import org.aspectj.lang.annotation.Before;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    WeatherType weatherType;
    LocalDate date;

}
