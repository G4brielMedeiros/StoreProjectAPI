package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "purchase")
    @JsonManagedReference
    private Payment payment;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonManagedReference
    private Client client;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.purchase")
    @EqualsAndHashCode.Exclude
    private Set<PurchaseItem> items = new HashSet<>();
}
