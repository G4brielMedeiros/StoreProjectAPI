package dev.gabriel.storeproject.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull private String street;

    @NonNull private String number;

    @NonNull private String complement;

    @NonNull private String postalCode;

    @JsonIgnore
    @ManyToOne
    @NonNull private Client client;

    @ManyToOne
    @NonNull private City city;
}
