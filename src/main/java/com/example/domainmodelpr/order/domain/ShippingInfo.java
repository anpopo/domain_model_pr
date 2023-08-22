package com.example.domainmodelpr.order.domain;

import jakarta.persistence.*;

@Embeddable
public class ShippingInfo {
    @Column(name = "shipping_message")
    private String message;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode", column = @Column(name = "shipping_zipcode")),
            @AttributeOverride(name = "address1", column = @Column(name = "shipping_primary_address")),
            @AttributeOverride(name = "address2", column = @Column(name = "shipping_secondary_address")),
    })
    private Address address;

    @Embedded
    private Receiver receiver;

}
