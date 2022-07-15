package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class State implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "state")
    private List<City> cities = new ArrayList<>();
}
