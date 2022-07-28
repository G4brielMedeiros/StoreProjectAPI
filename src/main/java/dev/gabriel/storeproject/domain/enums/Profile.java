package dev.gabriel.storeproject.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Profile {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT");

    private final int code;
    private final String description;

    public static Profile toEnum(Integer code) {
        if (code == null) return null;

        var clientType = Arrays.stream(Profile.values()).filter(c -> code.equals(c.code)).findAny();

        return clientType.orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + code));
    }
}
