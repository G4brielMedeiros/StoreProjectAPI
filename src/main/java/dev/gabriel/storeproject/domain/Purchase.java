package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
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

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss");
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        final StringBuffer sb = new StringBuffer();
        sb.append("Order number: ");
        sb.append(getId());
        sb.append("\nPurchase instant: ");
        sb.append(sdf.format(getInstant()));
        sb.append("\nClient: ");
        sb.append(getClient().getName());
        sb.append("\nPayment status: ");
        sb.append(getPayment().getStatus().getDescription());
        sb.append("\nDetails:\n");

        getItems().forEach(purchaseItem -> sb.append(purchaseItem.toString()));

        sb.append("Total: ");
        sb.append(nf.format(getTotalPrice()));
        return sb.toString();

    }
}
