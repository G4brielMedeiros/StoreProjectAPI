package dev.gabriel.storeproject.config;

import dev.gabriel.storeproject.service.database.DBService;
import dev.gabriel.storeproject.service.email.EmailService;
import dev.gabriel.storeproject.service.email.MockEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import java.text.ParseException;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig {

    final DBService dbService;
    final TemplateEngine templateEngine;
    final JavaMailSender javaMailSender;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService(templateEngine, javaMailSender);
    }
}
