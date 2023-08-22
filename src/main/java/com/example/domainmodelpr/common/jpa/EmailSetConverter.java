package com.example.domainmodelpr.common.jpa;

import com.example.domainmodelpr.member.domain.Email;
import com.example.domainmodelpr.member.domain.EmailSet;
import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EmailSetConverter implements AttributeConverter<EmailSet, String> {

    @Override
    public String convertToDatabaseColumn(EmailSet attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getEmails().stream()
                .map(Email::getEmail)
                .collect(Collectors.joining(","));
    }

    @Override
    public EmailSet convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return null;
        }

        return new EmailSet(
                Arrays.stream(dbData.split(","))
                        .map(Email::new)
                        .collect(Collectors.toSet())
        );
    }
}
