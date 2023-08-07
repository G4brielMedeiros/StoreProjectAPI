package dev.gabriel.storeproject.service.email;

import dev.gabriel.storeproject.domain.Client;
import dev.gabriel.storeproject.domain.Purchase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@RequiredArgsConstructor
public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    final TemplateEngine templateEngine;
    final JavaMailSender javaMailSender;

    public void sendOrderConfirmationEmail(Purchase purchase){
        SimpleMailMessage message = prepareSimpleMailMessageFromPurchase(purchase);
        sendEmail(message);
    }

    public void sendNewPasswordEmail(Client client, String newPassword){
        SimpleMailMessage message = prepareNewPasswordEmail(client, newPassword);
        sendEmail(message);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Client client, String newPassword) {
        var simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(client.getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Your new password");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText("Your new password is: " + newPassword);
        return simpleMailMessage;
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPurchase(Purchase purchase) {
        var simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(purchase.getClient().getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Order #" + purchase.getId() + " is confirmed!");
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(purchase.toString());
        return simpleMailMessage;
    }

    protected String htmlFromPurchaseTemplate(Purchase purchase) {
        var context = new Context();
        context.setVariable("purchase", purchase);
        return templateEngine.process("email/purchaseConfirmation", context);
    }

    public void sendOrderConfirmationHtmlEmail(Purchase purchase) {
        try {
            sendHtmlEmail(prepareMimeMessageFromPurchase(purchase));
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(purchase);
        }

    }

    protected MimeMessage prepareMimeMessageFromPurchase(Purchase purchase) throws MessagingException {
        var mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(purchase.getClient().getEmail());
        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setSubject("Order #" + purchase.getId() + " is confirmed!");
        mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
        mimeMessageHelper.setText(htmlFromPurchaseTemplate(purchase), true);
        return mimeMessage;
    }
}
