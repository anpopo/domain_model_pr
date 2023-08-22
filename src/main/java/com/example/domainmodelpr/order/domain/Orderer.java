package com.example.domainmodelpr.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Orderer {

    /*
    EmbeddedId 를 사용하기 위해 Embeddable 로 만든 id 를 사용하는 이점이 뭔지 잘 모르겠다.
    데이터 베이스에서 제공되는 대리키를 사용하는 것이 더 적합해 보이며 그 이유는 다음과 같다.
    1. 기본키 중복 걱정을 하지 않아도 된다.
    2. 미래에 있을 혹시 모를 변화에 변경 사항이 적게 대응이 가능하다.
    3. 일관된 방식으로 기본키 생성이 가능하기 때문에 예측 가능성이 높다.
    */

    @Column(name = "orderer_id")
    private Long memberId;

    @Column(name = "orderer_name")
    private String name;

}
