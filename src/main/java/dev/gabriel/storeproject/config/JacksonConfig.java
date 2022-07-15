package dev.gabriel.storeproject.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.gabriel.storeproject.domain.CardPayment;
import dev.gabriel.storeproject.domain.InvoicePayment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig  {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder() {
          public void configure(ObjectMapper objectMapper) {
              objectMapper.registerSubtypes(CardPayment.class, InvoicePayment.class);
                super.configure(objectMapper);
          }
        };
    }
}
