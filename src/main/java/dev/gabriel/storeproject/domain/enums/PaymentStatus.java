package dev.gabriel.storeproject.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@AllArgsConstructor
@Getter
public enum PaymentStatus {

    PENDING(1, "pending"),
    PAID(2, "paid"),
    CANCELED(3, "cancelled");

    private final int code;
    private final String description;

    public static PaymentStatus toEnum(Integer code) {
        if (code == null) return null;

        var paymentState = Stream.of(PaymentStatus.values()).filter(c -> code.equals(c.code)).findAny();

        return paymentState.orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + code));
    }
}
