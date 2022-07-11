package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.gabriel.storeproject.domain.enums.ClientType;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Client implements Serializable {

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
    private String email;

    @NonNull
    @EqualsAndHashCode.Exclude
    private String governmentRegistration;

    @NonNull
    @EqualsAndHashCode.Exclude
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer type;

    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "client")
    private List<Address> addresses = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ElementCollection
    @CollectionTable(name = "phone_number")
    @Column(name = "phone_number")
    private Set<String> phoneNumbers = new HashSet<>();


    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "client")
    @JsonBackReference
    private List<Purchase> purchases = new ArrayList<>();

    public Client(@NonNull String name, @NonNull String email, @NonNull String governmentRegistration, @NonNull ClientType type) {
        this.name = name;
        this.email = email;
        this.governmentRegistration = governmentRegistration;
        this.type = type.getCode();
    }

    public void setType(ClientType type) {
        this.type = type.getCode();
    }

    public ClientType getType() {
        return ClientType.toEnum(this.type);
    }
}
