package com.example.domainmodelpr.product.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Column(name = "product_id")
    @Id @GeneratedValue
    private Long productId;

}
