package dev.gabriel.storeproject.config;

import dev.gabriel.storeproject.service.email.EmailService;
import dev.gabriel.storeproject.service.email.SmtpEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

@Configuration
@Profile("prod")
@RequiredArgsConstructor
public class ProdConfig {

    final MailSender mailSender;
    final TemplateEngine templateEngine;
    final JavaMailSender javaMailSender;

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService(templateEngine, javaMailSender, mailSender);
    }
}
