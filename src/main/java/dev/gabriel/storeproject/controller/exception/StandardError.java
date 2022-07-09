package dev.gabriel.storeproject.controller.exception;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {
    private Integer status;
    private String msg;
    private Long timeStamp;
}
