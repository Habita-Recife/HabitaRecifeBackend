package br.com.habita_recife.habita_recife_backend.features_authentication.dto;

import br.com.habita_recife.habita_recife_backend.features_authentication.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    private String username;
    private String email;
    private String password;
    private String token;
    private Set<Role> roles = new HashSet<>();

    public UserLoginDTO(String username, String email, String token, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.token = token;
        this.roles = roles;
    }
}
