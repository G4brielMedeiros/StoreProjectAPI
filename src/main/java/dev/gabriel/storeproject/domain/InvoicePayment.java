package dev.gabriel.storeproject.domain;

import dev.gabriel.storeproject.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serial;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class InvoicePayment extends Payment {

    @Serial
    private static final long serialVersionUID = 1L;

    private Date expiringDate;
    private Date paymentDate;

    public InvoicePayment(PaymentStatus status, Purchase purchase, Date expiringDate, Date paymentDate) {
        super(status, purchase);
        this.expiringDate = expiringDate;
        this.paymentDate = paymentDate;

    }
}
