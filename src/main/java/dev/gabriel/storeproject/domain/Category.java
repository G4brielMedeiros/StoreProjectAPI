package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @EqualsAndHashCode.Exclude
    private String name;

    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    @ManyToMany(mappedBy = "categories")
    private List<Product> products = new ArrayList<>();
}
