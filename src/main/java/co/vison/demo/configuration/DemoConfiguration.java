package co.vison.demo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class DemoConfiguration {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
