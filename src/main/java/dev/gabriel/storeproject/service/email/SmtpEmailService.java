package dev.gabriel.storeproject.service.email;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import javax.mail.internet.MimeMessage;


public class SmtpEmailService extends AbstractEmailService{

    final MailSender mailSender;
    private static final Logger LOGGER = LoggerFactory.getLogger(SmtpEmailService.class);

    public SmtpEmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender, MailSender mailSender) {
        super(templateEngine, javaMailSender);
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(SimpleMailMessage message) {
        LOGGER.info("Sending email...");
        mailSender.send(message);
        LOGGER.info("Email sent.");
    }

    @Override
    public void sendHtmlEmail(MimeMessage message) {
        LOGGER.info("Sending HTML email...");
        javaMailSender.send(message);
        LOGGER.info("Email sent.");
    }
}
