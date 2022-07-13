package dev.gabriel.storeproject.dto;

import dev.gabriel.storeproject.domain.Client;
import dev.gabriel.storeproject.service.validation.ClientUpdate;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@ClientUpdate
public class ClientDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Name must not be empty.")
    @Length(min = 2, max = 120, message = "Name must be between 2 and 120 characters.")
    private String name;

    @NotEmpty(message = "Email must not be empty.")
    @Email(message = "Invalid email.")
    private String email;

    public ClientDTO(Client obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
    }

}
