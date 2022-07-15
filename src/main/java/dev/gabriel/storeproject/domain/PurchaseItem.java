package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@Entity
public class PurchaseItem implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @EmbeddedId
    @JsonIgnore
    private PurchaseItemPK id = new PurchaseItemPK();
    private Double discount;
    private Integer quantity;
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

    public void setPurchase(Purchase purchase) {
        id.setPurchase(purchase);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public double getSubTotal() {
        return (this.price - this.discount) * this.quantity;
    }
}
