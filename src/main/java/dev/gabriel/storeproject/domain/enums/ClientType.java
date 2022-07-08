package dev.gabriel.storeproject.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ClientType {

    BUSINESS(2, "Business"),
    PERSON(1, "Person");

    private final int code;
    private final String description;

    public static ClientType toEnum(Integer code) {

        if (code == null) return null;

        var clientType = Arrays.stream(ClientType.values()).filter(c -> code.equals(c.code)).findAny();

        return clientType.orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + code));

    }
}
