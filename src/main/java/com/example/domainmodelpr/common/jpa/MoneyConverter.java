package com.example.domainmodelpr.common.jpa;

import com.example.domainmodelpr.order.domain.Money;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MoneyConverter implements AttributeConverter<Money, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Money attribute) {
        return attribute == null ? 0 : attribute.getValue();
    }

    @Override
    public Money convertToEntityAttribute(Integer dbData) {
        return dbData == null ? Money.zero() : new Money(dbData);
    }
}
