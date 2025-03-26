package br.com.habita_recife.habita_recife_backend.features_authentication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public enum Role {

    ADMIN("admin"),
    PORTEIRO("porteiro"),
    MORADOR("morador");

    private String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}