package com.example.demo.Entity;

import lombok.Getter;

@Getter
public enum CardType {
    GOLD(50000),
    TITANIUM(100000);

    private final int cashLimit;

    CardType(int cashLimit){
        this.cashLimit = cashLimit;
    }
}
