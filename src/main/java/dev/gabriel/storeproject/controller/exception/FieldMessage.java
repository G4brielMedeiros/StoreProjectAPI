package dev.gabriel.storeproject.controller.exception;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldMessage implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    private String fieldName;
    private String message;

}
