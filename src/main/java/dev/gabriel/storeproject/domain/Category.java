package dev.gabriel.storeproject.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Category implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Product> products = new ArrayList<>();
}
