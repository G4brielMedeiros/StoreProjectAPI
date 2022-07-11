package dev.gabriel.storeproject.domain;

import dev.gabriel.storeproject.domain.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CardPayment extends Payment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer installments;

    public CardPayment(PaymentStatus status, Purchase purchase, Integer installments) {
        super(status, purchase);
        this.installments = installments;
    }

}
