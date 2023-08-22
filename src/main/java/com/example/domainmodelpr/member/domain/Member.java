package com.example.domainmodelpr.member.domain;

import com.example.domainmodelpr.common.jpa.EmailSetConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {

    @Column(name = "member_id")
    @Id @GeneratedValue
    private Long memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "emails")
    @Convert(converter = EmailSetConverter.class)
    private EmailSet emailSet;
}
