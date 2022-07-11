package dev.gabriel.storeproject.domain;

import dev.gabriel.storeproject.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serial;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CreditPayment extends Payment {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer installments;

    public CreditPayment(PaymentStatus status, Purchase purchase, Integer installments) {
        super(status, purchase);
        this.installments = installments;
    }

}
