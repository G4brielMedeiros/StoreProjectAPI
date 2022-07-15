package dev.gabriel.storeproject.dto;

import dev.gabriel.storeproject.domain.Product;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
public class ProductDTO implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private double price;

    public ProductDTO(Product obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.price = obj.getPrice();
    }
}
