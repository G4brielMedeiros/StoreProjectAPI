package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.gabriel.storeproject.domain.enums.ClientType;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Client implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;
    private String governmentRegistration;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer type;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "phone_number")
    @Column(name = "phone_number")
    private Set<String> phoneNumbers = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Purchase> purchases = new ArrayList<>();

    public Client(String name, String email, String governmentRegistration, ClientType type) {
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
