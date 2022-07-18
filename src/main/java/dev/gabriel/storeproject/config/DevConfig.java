package dev.gabriel.storeproject.config;

import dev.gabriel.storeproject.service.DBService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class DevConfig {

    final DBService dbService;


    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {


        if(!strategy.equals("create")) {
            return false;
        }

        dbService.instantiateTestDatabase();
        return true;
    }
}
