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

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @NonNull private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "purchase")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @NonNull private Client client;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    @NonNull private Address deliveryAddress;

    @OneToMany(mappedBy = "id.purchase")
    private Set<PurchaseItem> items = new HashSet<>();

    public double getTotalPrice() {
        return items.stream().mapToDouble(PurchaseItem::getSubTotal).sum();
    }
}
