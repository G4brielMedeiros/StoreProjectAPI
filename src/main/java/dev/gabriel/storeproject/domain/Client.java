package dev.gabriel.storeproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.gabriel.storeproject.domain.enums.ClientType;
import dev.gabriel.storeproject.domain.enums.Profile;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(unique = true)
    private String email;
    private String governmentRegistration;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer type;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "phone_number")
    @Column(name = "phone_number")
    private Set<String> phoneNumbers = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable
    private Set<Integer> profiles = new HashSet<>();

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Purchase> purchases = new ArrayList<>();

    public Client() {
        addProfile(Profile.CLIENT);
    }

    public Client(String name, String email, String governmentRegistration, ClientType type, String password) {
        this.name = name;
        this.email = email;
        this.governmentRegistration = governmentRegistration;
        this.type = type.getCode();
        this.password = password;
        addProfile(Profile.CLIENT);
    }

    public void setType(ClientType type) {
        this.type = type.getCode();
    }

    public ClientType getType() {
        return ClientType.toEnum(this.type);
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(Profile::toEnum).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        profiles.add(profile.getCode());
    }
}
