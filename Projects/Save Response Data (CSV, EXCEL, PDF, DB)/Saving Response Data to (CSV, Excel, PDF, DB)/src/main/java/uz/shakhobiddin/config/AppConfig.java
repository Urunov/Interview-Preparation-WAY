package uz.shakhobiddin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    //Used to make HTTP requests
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

