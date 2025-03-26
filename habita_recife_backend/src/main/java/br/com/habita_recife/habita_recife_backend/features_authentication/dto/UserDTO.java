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
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private Integer version;
    private String token;
    private Set<Role> roles = new HashSet<>();


}
