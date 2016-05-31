package de.adesso.iliasdownloader3.service.impl.soap.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LoginType {
    DEFAULT("login"), LDAP("loginLDAP"), CAS("loginCAS");

    private final String loginMethodName;
}