package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
public class PurchaseItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @JsonIgnore
    private PurchaseItemPK id = new PurchaseItemPK();

    @EqualsAndHashCode.Exclude
    private Double discount;

    @EqualsAndHashCode.Exclude
    private Integer quantity;

    @EqualsAndHashCode.Exclude
    private Double price;

    public PurchaseItem(Purchase purchase, Product product, Double discount, Integer quantity, Double price) {
        this.id.setPurchase(purchase);
        this.id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnore
    public Purchase getPurchase() {
        return id.getPurchase();
    }

    public Product getProduct() {
        return id.getProduct();
    }
}
