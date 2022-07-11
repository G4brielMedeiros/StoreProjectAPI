package dev.gabriel.storeproject.domain;

import dev.gabriel.storeproject.domain.enums.PaymentStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @Setter
    private Integer id;

    @EqualsAndHashCode.Exclude
    private Integer status;

    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    @OneToOne
    @JoinColumn(name = "purchase_id")
    @MapsId
    private Purchase purchase;

    public Payment(PaymentStatus status, Purchase purchase) {
        this.status = (status == null) ? null : status.getCode();
        this.purchase = purchase;
    }

    public PaymentStatus getStatus() {
        return PaymentStatus.toEnum(status);
    }

    public void setStatus(PaymentStatus status) {
        this.status = status.getCode();
    }
}
