package com.example.demo.Entity;

import lombok.Getter;

@Getter
public enum TenureMonths {
    THREE(3),
    SIX(6),
    NINE(9),
    TWELVE(12);

    private final int value;

    TenureMonths(int value) {
        this.value = value;
    }

    public static TenureMonths fromValue(int value) {
        for (TenureMonths tenure : TenureMonths.values()) {
            if (tenure.getValue() == value) {
                return tenure;
            }
        }
        throw new IllegalArgumentException("Invalid tenure value: " + value);
    }
}
