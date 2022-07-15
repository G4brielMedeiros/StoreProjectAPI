package dev.gabriel.storeproject.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class City implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull private String name;


    @ManyToOne
    @NonNull private State state;
}
