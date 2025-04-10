package br.com.habita_recife.habita_recife_backend.domain.enums;

import jakarta.persistence.*;

public enum RoleName {
    ADMIN("admin"),
    SINDICO("sindico"),
    PREFEITURA("prefeitura"),
    MORADOR("morador"),
    PORTEIRO("porteiro");

    @Column(nullable = false, unique = true)
    private String role;

    RoleName(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static RoleName fromString(String role) {
        for (RoleName roleName : RoleName.values()) {
            if (roleName.getRole().equalsIgnoreCase(role)) {
                return roleName;
            }
        }
        throw new IllegalArgumentException("Role inválida: " + role);
    }
}