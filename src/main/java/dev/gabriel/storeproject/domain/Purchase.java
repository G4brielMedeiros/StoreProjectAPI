package dev.gabriel.storeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Purchase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @EqualsAndHashCode.Exclude
    private Date instant;

    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "purchase")
    private Payment payment;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;
}
