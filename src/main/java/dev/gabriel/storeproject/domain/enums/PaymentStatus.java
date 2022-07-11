package dev.gabriel.storeproject.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum PaymentStatus {

    PENDING(1, "Pending"),
    PAID(2, "Paid"),
    CANCELED(3, "Cancelled");

    private final int code;
    private final String description;

    public static PaymentStatus toEnum(Integer code) {

        if (code == null) return null;

        var paymentState = Arrays.stream(PaymentStatus.values()).filter(c -> code.equals(c.code)).findAny();

        return paymentState.orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + code));

    }
}
