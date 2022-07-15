package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Purchase implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "purchase")
    private Payment payment;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.purchase")
    private Set<PurchaseItem> items = new HashSet<>();
}
