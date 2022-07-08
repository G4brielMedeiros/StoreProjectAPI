package dev.gabriel.storeproject.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @EqualsAndHashCode.Exclude
    private String street;

    @NonNull
    @EqualsAndHashCode.Exclude
    private String number;

    @NonNull
    @EqualsAndHashCode.Exclude
    private String complement;

    @NonNull
    @EqualsAndHashCode.Exclude
    private String postalCode;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ManyToOne
    private Client client;

    @NonNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private City city;


}
