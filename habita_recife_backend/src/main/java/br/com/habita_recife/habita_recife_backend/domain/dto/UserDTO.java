package br.com.habita_recife.habita_recife_backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private Integer version;
    private String token;
    private Set<String> roles = new HashSet<>();

    public UserDTO(String username, String email, String token, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.token = token;
        this.roles = roles;
    }
}
