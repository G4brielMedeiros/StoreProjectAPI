package dev.gabriel.storeproject.config;

import dev.gabriel.storeproject.service.database.DBService;
import dev.gabriel.storeproject.service.email.EmailService;
import dev.gabriel.storeproject.service.email.SmtpEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import java.text.ParseException;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class DevConfig {

    final DBService dbService;
    final MailSender mailSender;
    final TemplateEngine templateEngine;
    final JavaMailSender javaMailSender;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if (!strategy.equals("create")) return false;
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService(templateEngine, javaMailSender, mailSender);
    }
}
