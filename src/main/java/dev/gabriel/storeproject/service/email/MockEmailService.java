package dev.gabriel.storeproject.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService  {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailService.class);

    public MockEmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        super(templateEngine, javaMailSender);
    }

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOGGER.info("Sending simulated email...");
        LOGGER.info(message.toString());
        LOGGER.info("Email sent.");
    }

    @Override
    public void sendHtmlEmail(MimeMessage message) {
        LOGGER.info("Sending simulated HTML email...");
        LOGGER.info(message.toString());
        LOGGER.info("Email sent.");
    }
}
