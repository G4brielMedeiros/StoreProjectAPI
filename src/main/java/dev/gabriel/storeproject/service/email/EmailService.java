package dev.gabriel.storeproject.service.email;

import dev.gabriel.storeproject.domain.Purchase;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Purchase purchase);

    void sendEmail(SimpleMailMessage message);

    void sendOrderConfirmationHtmlEmail(Purchase purchase);

    void sendHtmlEmail(MimeMessage message);

}
