package dev.gabriel.storeproject.config;

import dev.gabriel.storeproject.service.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig {

    final DBService dbService;


    @Bean
    public boolean instantiateDatabase() throws ParseException {

        dbService.instantiateTestDatabase();
        return true;
    }
}
