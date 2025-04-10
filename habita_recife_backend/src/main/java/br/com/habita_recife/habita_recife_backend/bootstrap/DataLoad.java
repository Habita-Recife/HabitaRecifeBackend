package br.com.habita_recife.habita_recife_backend.bootstrap;

import br.com.habita_recife.habita_recife_backend.domain.model.Prefeitura;
import br.com.habita_recife.habita_recife_backend.domain.repository.PrefeituraRepository;
import br.com.habita_recife.habita_recife_backend.domain.model.Role;
import br.com.habita_recife.habita_recife_backend.domain.enums.RoleName;
import br.com.habita_recife.habita_recife_backend.domain.model.User;
import br.com.habita_recife.habita_recife_backend.domain.repository.RoleRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataLoad implements CommandLineRunner {

    private final PrefeituraRepository prefeituraRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoad(PrefeituraRepository prefeituraRepository, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.prefeituraRepository = prefeituraRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (prefeituraRepository.count() == 0) {
            Prefeitura prefeitura = new Prefeitura();
            prefeitura.setNomePrefeitura("Prefeitura do Recife");
            prefeituraRepository.save(prefeitura);
            System.out.println("Dados da Prefeitura inseridos com sucesso!");
        }

        String emailPrefeitura = "prefeitura@exemplo.com";
        if (userRepository.findByEmail(emailPrefeitura).isEmpty()) {
            User user = new User();
            user.setUsername("prefeitura");
            user.setEmail(emailPrefeitura);
            user.setPassword(passwordEncoder.encode("RecifePE12345678"));

            Optional<Role> existingRole = roleRepository.findByRole(RoleName.PREFEITURA);
            Role rolePrefeitura;

            if (existingRole.isPresent()) {
                rolePrefeitura = existingRole.get();
            } else {
                Role newRole = new Role(RoleName.PREFEITURA);
                rolePrefeitura = roleRepository.save(newRole);
            }
            // Busca a role de PREFEITURA ou cria uma nova, se necessário
//            Role rolePrefeitura = roleRepository.findByRole(RoleName.PREFEITURA)
//                    .orElseGet(() -> {
//                        Role newRole = new Role(RoleName.PREFEITURA);
//                        return roleRepository.save(newRole);
//                    });
            user.getRoles().add(rolePrefeitura);
            userRepository.save(user);
            System.out.println("Usuário Prefeitura inserido com sucesso!");
        }

    }
}
