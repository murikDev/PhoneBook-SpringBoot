package ru.murik.phone_book3.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_DELETE, ROLE_CREATE;

    @Override
    public String getAuthority() {
        return name();
    }
}
