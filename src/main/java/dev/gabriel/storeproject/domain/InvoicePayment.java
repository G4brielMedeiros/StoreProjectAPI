package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.gabriel.storeproject.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class InvoicePayment extends Payment implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date expiringDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date paymentDate;

    public InvoicePayment(PaymentStatus status, Purchase purchase, Date expiringDate, Date paymentDate) {
        super(status, purchase);
        this.expiringDate = expiringDate;
        this.paymentDate = paymentDate;
    }
}
