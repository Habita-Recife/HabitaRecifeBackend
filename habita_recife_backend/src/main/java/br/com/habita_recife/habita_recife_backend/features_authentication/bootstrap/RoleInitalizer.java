package br.com.habita_recife.habita_recife_backend.features_authentication.bootstrap;

import br.com.habita_recife.habita_recife_backend.features_authentication.model.Role;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.RoleName;
import br.com.habita_recife.habita_recife_backend.features_authentication.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RoleInitalizer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleInitalizer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        Arrays.stream(RoleName.values()).forEach(roleEnum -> {
            if (roleRepository.findByRole(roleEnum).isEmpty()) {
                roleRepository.save(new Role(roleEnum));
                System.out.println("Role criada: " + roleEnum);
            }
        });
    }
}
