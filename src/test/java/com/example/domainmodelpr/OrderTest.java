package com.example.domainmodelpr;

import com.example.domainmodelpr.order.domain.Money;
import com.example.domainmodelpr.order.domain.Order;
import com.example.domainmodelpr.order.domain.OrderLine;
import com.example.domainmodelpr.order.domain.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DataJpaTest
public class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    @DisplayName("값 타입에 대한 정보가 수정되는 경우 모든 데이터가 삭제되고 다시 인서트 되는 여부 확인 - 내부 값이 바뀌는 경우")
    @Test
    @Transactional
    @Commit
    void orderLineUpdateTest() {
        OrderLine orderLine1 = new OrderLine(1L, new Money(10), 10);
        OrderLine orderLine2 = new OrderLine(2L, new Money(20), 20);
        OrderLine orderLine3 = new OrderLine(3L, new Money(30), 30);

        Order order = orderRepository.saveAndFlush(new Order(List.of(orderLine1, orderLine2, orderLine3)));

        // 이경우 update query 가 나가며 값이 업데이트 됨. - delete 쿼리는 발생하지 않음
        order.updateOrderLine();
    }

    @DisplayName("값 타입에 대한 정보가 수정되는 경우 모든 데이터가 삭제되고 다시 인서트 되는 여부 확인 - 불변 객체로써 값이 바뀌는 경우")
    @Test
    @Transactional
    @Commit
    void orderLineUpdateTest2() {
        OrderLine orderLine1 = new OrderLine(1L, new Money(10), 10);
        OrderLine orderLine2 = new OrderLine(2L, new Money(20), 20);
        OrderLine orderLine3 = new OrderLine(3L, new Money(30), 30);

        Order order = orderRepository.saveAndFlush(new Order(new ArrayList<>(List.of(orderLine1, orderLine2, orderLine3))));

        OrderLine newOrderLine1 = new OrderLine(1L, new Money(10), 10);
        OrderLine newOrderLine2 = new OrderLine(2L, new Money(20), 20);
        OrderLine newOrderLine3 = new OrderLine(3L, new Money(30), 30);

        // 이경우 orderId 를 조건으로 order line 에 delete query 가 발생하며 이후 새로운 line 인서트
        order.replaceOrderLine(List.of(newOrderLine1, newOrderLine2, newOrderLine3));
    }

    @DisplayName("값 타입에 대한 정보가 수정되는 경우 모든 데이터가 삭제되고 다시 인서트 되는 여부 확인 - 리스트에 아이템이 추가 / 삭제 되는 경우")
    @Test
    @Transactional
    @Commit
    void orderLineUpdateTest3() {
        OrderLine orderLine1 = new OrderLine(1L, new Money(10), 10);
        OrderLine orderLine2 = new OrderLine(2L, new Money(20), 20);
        OrderLine orderLine3 = new OrderLine(3L, new Money(30), 30);

        Order order = orderRepository.saveAndFlush(new Order(new ArrayList<>(List.of(orderLine1, orderLine2, orderLine3))));

        OrderLine newOrderLine1 = new OrderLine(1L, new Money(10), 10);

        // 기존 3개의 order line 인서트 이후 업데이트 로직이 발생하고 기존의 값에 대해서 order line index 를 업데이트 하기 위한 쿼리가 발생한다.
        // 이후 새로운 값을 인서트 한다.
        order.addOrderLine(Collections.singletonList(newOrderLine1));
    }
}
