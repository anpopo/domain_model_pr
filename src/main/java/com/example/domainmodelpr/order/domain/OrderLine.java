package com.example.domainmodelpr.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderLine {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "price")
    private Money price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "amounts")
    private Money amounts;

    public Long getProductId() {
        return productId;
    }

    public Money getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getAmounts() {
        return amounts;
    }

    protected OrderLine() {
    }

    public OrderLine(Long productId, Money price, int quantity) {
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.amounts = new Money(price.getValue() * quantity);
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
        this.amounts = new Money(
                this.price.getValue() * quantity
        );
    }
}
