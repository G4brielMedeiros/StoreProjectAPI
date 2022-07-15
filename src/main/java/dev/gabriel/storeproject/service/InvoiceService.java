package dev.gabriel.storeproject.service;

import dev.gabriel.storeproject.domain.InvoicePayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class InvoiceService {

    public void fillInvoicePayment(InvoicePayment payment, Date purchaseInstant) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(purchaseInstant);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        payment.setExpiringDate(cal.getTime());
    }
}
