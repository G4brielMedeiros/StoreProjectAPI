package dev.gabriel.storeproject.dto;

import dev.gabriel.storeproject.service.validation.ClientInsert;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ClientInsert
public class NewClientDTO implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Name must not be empty.")
    @Length(min = 2, max = 120, message = "Name must be between 2 and 120 characters.")
    private String name;

    @NotEmpty(message = "Email must not be empty.")
    @Email(message = "Invalid email.")
    private String email;

    @NotEmpty(message = "Government registration number must not be empty.")
    private String governmentRegistration;

    @NotNull(message = "Client type must not be empty.")
    private Integer type;

    @NotEmpty(message = "Street must not be empty.")
    private String street;

    private String number;

    private String complement;

    @NotEmpty(message = "Postal code must not be empty.")
    private String postalCode;

    @NotEmpty(message = "Phone number must not be empty.")
    private String phoneNumber1;

    private String phoneNumber2;

    private String phoneNumber3;

    @NotNull(message = "City ID must not be empty")
    private Integer cityId;

}
