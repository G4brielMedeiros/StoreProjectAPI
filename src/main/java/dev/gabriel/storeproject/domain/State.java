package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class State implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @EqualsAndHashCode.Exclude
    private String name;

    @EqualsAndHashCode.Exclude
    @JsonBackReference
    @OneToMany(mappedBy = "state")
    private List<City> cities = new ArrayList<>();
}
