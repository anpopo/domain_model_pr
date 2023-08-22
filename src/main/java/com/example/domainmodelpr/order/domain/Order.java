package com.example.domainmodelpr.order.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "purchase_order")
public class Order {

    @Column(name = "order_id")
    @Id @GeneratedValue
    private Long orderId;

    @Column(name = "total_amount")
    private Money totalAmount;

    @ElementCollection(fetch = FetchType.LAZY)  // default LAZY
    @CollectionTable(
            name = "order_line",
            joinColumns = @JoinColumn(name = "order_id")
    )
    @OrderColumn(name = "order_line_index")
    private List<OrderLine> orderLines;

    @Embedded
    private Orderer orderer;

    @Embedded
    private ShippingInfo shippingInfo;


    public Order() {
    }

    // test 용 임의 생성
    public Order(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void updateOrderLine() {
        this.orderLines.forEach(orderLine -> {
            orderLine.updateQuantity(orderLine.getQuantity() + 3);
        });
    }
    public void replaceOrderLine(List<OrderLine> newOrderLines) {
        this.orderLines = newOrderLines;
    }

    public void addOrderLine(List<OrderLine> newOrderLines) {
        this.orderLines.addAll(newOrderLines);
    }
}
