package br.com.habita_recife.habita_recife_backend.features_authentication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Role")
    private Long idRole;

    @Column(nullable = false)
    private String nomeRole;


    public enum Values {

        ADMIN(1L),
        PORTEIRO(2L),
        MORADOR(3L);

        long roleId;

        Values(long roleId) {
            this.roleId = roleId;
        }

        public long getRoleId() {
            return roleId;
        }
    }



}