package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @EqualsAndHashCode.Exclude
    private String name;

    @NonNull
    @EqualsAndHashCode.Exclude
    private double price;

    @EqualsAndHashCode.Exclude
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "id.product")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<PurchaseItem> items = new HashSet<>();

    @JsonIgnore
    public List<Purchase> getPurchases() {
        return items.stream().map(PurchaseItem::getPurchase).collect(Collectors.toList());
    }


}
