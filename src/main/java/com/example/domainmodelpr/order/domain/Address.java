package com.example.domainmodelpr.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "primary_address")
    private String address1;

    @Column(name = "secondary_address")
    private String address2;
}
