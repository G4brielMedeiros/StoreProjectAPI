package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.gabriel.storeproject.domain.enums.PaymentStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @Id @Getter @Setter
    private Integer id;

    private Integer status;

    @Getter @Setter
    @OneToOne
    @JoinColumn(name = "purchase_id")
    @MapsId
    @JsonIgnore
    private Purchase purchase;

    public Payment(PaymentStatus status, Purchase purchase) {
        this.status = status.getCode();
        this.purchase = purchase;
    }

    public PaymentStatus getStatus() {
        return PaymentStatus.toEnum(status);
    }

    public void setStatus(PaymentStatus status) {
        this.status = status.getCode();
    }
}
