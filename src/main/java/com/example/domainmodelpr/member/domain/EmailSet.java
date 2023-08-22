package com.example.domainmodelpr.member.domain;

import java.util.HashSet;
import java.util.Set;

public class EmailSet {

    private Set<Email> emails;

    public EmailSet(Set<Email> emails) {
        this.emails = new HashSet<>();
        this.emails.addAll(emails);
    }

    public Set<Email> getEmails() {
        return this.emails;
    }
}
