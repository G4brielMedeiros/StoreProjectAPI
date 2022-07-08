package dev.gabriel.storeproject.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class City implements Serializable {

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
    @ToString.Exclude
    @ManyToOne
    private State state;
}
