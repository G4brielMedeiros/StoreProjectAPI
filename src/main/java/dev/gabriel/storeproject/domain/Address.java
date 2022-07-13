package dev.gabriel.storeproject.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String number;
    private String complement;
    private String postalCode;

    @JsonIgnore
    @ManyToOne
    private Client client;

    @ToString.Exclude
    @ManyToOne
    private City city;

    public Address(String street, String number, String complement, String postalCode, Client client, City city) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.postalCode = postalCode;
        this.client = client;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
