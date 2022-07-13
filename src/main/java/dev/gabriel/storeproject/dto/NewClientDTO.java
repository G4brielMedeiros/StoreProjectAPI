package dev.gabriel.storeproject.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewClientDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String governmentRegistration;
    private Integer type;

    private String street;
    private String number;
    private String complement;
    private String postalCode;

    private String phoneNumber1;
    private String phoneNumber2;
    private String phoneNumber3;

    private Integer cityId;

}
