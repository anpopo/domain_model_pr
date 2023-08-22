package com.example.domainmodelpr.order.domain;

public class Money {
    private final int value;

    public Money(int value) {
        this.value = value;
    }

    public static Money zero() {
        return new Money(0);
    }

    public int getValue() {
        return value;
    }
}
